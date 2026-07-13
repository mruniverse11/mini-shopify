package com.minishopify.product.service;

import com.minishopify.product.dto.ProductRequest;
import com.minishopify.product.dto.ProductResponse;
import com.minishopify.product.entity.Product;
import com.minishopify.product.repository.ProductRepository;
import com.minishopify.store.entity.Store;
import com.minishopify.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.minishopify.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ProductResponse createProduct(Long storeId, ProductRequest request) {
        Store store = findStore(storeId);

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .imageUrl(request.getImageUrl())
                .store(store)
                .createdAt(LocalDateTime.now())
                .build();

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    public List<ProductResponse> getProductsByStore(Long storeId) {
        findStore(storeId);

        return productRepository.findByStoreId(storeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse getProduct(Long storeId, Long productId) {
        Product product = findProduct(storeId, productId);
        return mapToResponse(product);
    }

    public ProductResponse updateProduct(
            Long storeId,
            Long productId,
            ProductRequest request
    ) {
        Product product = findProduct(storeId, productId);

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setImageUrl(request.getImageUrl());

        Product updatedProduct = productRepository.save(product);

        return mapToResponse(updatedProduct);
    }

    public void deleteProduct(Long storeId, Long productId) {
        Product product = findProduct(storeId, productId);
        productRepository.delete(product);
    }

    private Store findStore(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

    }

    private Product findProduct(Long storeId, Long productId) {
        return productRepository.findByIdAndStoreId(productId, storeId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .storeId(product.getStore().getId())
                .storeName(product.getStore().getStoreName())
                .createdAt(product.getCreatedAt())
                .build();
    }
}