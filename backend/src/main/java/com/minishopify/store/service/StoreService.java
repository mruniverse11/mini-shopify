package com.minishopify.store.service;

import com.minishopify.auth.entity.User;
import com.minishopify.auth.repository.UserRepository;
import com.minishopify.store.dto.StoreRequest;
import com.minishopify.store.dto.StoreResponse;
import com.minishopify.store.entity.Store;
import com.minishopify.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public StoreResponse createStore(Long ownerId, StoreRequest request) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (storeRepository.findByOwner(owner).isPresent()) {
            throw new RuntimeException("Merchant already has a store");
        }

        String slug = generateSlug(request.getStoreName());

        if (storeRepository.existsBySlug(slug)) {
            throw new RuntimeException("Store slug already exists");
        }

        Store store = Store.builder()
                .storeName(request.getStoreName())
                .slug(slug)
                .description(request.getDescription())
                .owner(owner)
                .createdAt(LocalDateTime.now())
                .build();

        Store savedStore = storeRepository.save(store);

        return StoreResponse.builder()
                .id(savedStore.getId())
                .storeName(savedStore.getStoreName())
                .slug(savedStore.getSlug())
                .description(savedStore.getDescription())
                .ownerEmail(owner.getEmail())
                .message("Store created successfully")
                .build();
    }

    private String generateSlug(String storeName) {
        return storeName
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
    }
}