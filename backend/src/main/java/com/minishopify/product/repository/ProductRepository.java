package com.minishopify.product.repository;

import com.minishopify.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStoreId(Long storeId);

    Optional<Product> findByIdAndStoreId(Long productId, Long storeId);
}