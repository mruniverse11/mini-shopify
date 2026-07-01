package com.minishopify.product.repository;

import com.minishopify.product.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.minishopify.store.entity.Store;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStore(Store store);
}
