package com.minishopify.store.controller;

import com.minishopify.product.dto.ProductResponse;
import com.minishopify.product.service.ProductService;
import com.minishopify.store.entity.Store;
import com.minishopify.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.minishopify.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/public/stores")
@RequiredArgsConstructor
public class PublicStoreController {

    private final StoreRepository storeRepository;
    private final ProductService productService;

    @GetMapping("/{slug}")
    public StorefrontResponse getStorefront(@PathVariable String slug) {
        Store store = storeRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        List<ProductResponse> products =
                productService.getProductsByStore(store.getId());

        return new StorefrontResponse(
                store.getId(),
                store.getStoreName(),
                store.getSlug(),
                store.getDescription(),
                products
        );
    }
}