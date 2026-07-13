package com.minishopify.store.controller;

import com.minishopify.product.dto.ProductResponse;

import java.util.List;

public record StorefrontResponse(
        Long storeId,
        String storeName,
        String slug,
        String description,
        List<ProductResponse> products
) {
}