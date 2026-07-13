package com.minishopify.product.controller;

import com.minishopify.product.dto.ProductRequest;
import com.minishopify.product.dto.ProductResponse;
import com.minishopify.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores/{storeId}/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @PathVariable Long storeId,
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse response =
                productService.createProduct(storeId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<ProductResponse> getProducts(
            @PathVariable Long storeId
    ) {
        return productService.getProductsByStore(storeId);
    }

    @GetMapping("/{productId}")
    public ProductResponse getProduct(
            @PathVariable Long storeId,
            @PathVariable Long productId
    ) {
        return productService.getProduct(storeId, productId);
    }

    @PutMapping("/{productId}")
    public ProductResponse updateProduct(
            @PathVariable Long storeId,
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest request
    ) {
        return productService.updateProduct(
                storeId,
                productId,
                request
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long storeId,
            @PathVariable Long productId
    ) {
        productService.deleteProduct(storeId, productId);
        return ResponseEntity.noContent().build();
    }
}