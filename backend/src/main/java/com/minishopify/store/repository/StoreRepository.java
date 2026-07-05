package com.minishopify.store.repository;

import com.minishopify.auth.entity.User;
import com.minishopify.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findBySlug(String slug);
    Optional<Store> findByOwner(User owner);
    boolean existsBySlug(String slug);
}