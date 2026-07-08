package com.minishopify.store.controller;

import com.minishopify.store.dto.StoreRequest;
import com.minishopify.store.dto.StoreResponse;
import com.minishopify.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public StoreResponse createStore(
            @RequestParam Long ownerId,
            @Valid @RequestBody StoreRequest request
    ) {
        return storeService.createStore(ownerId, request);
    }
}