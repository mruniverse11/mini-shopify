package com.minishopify.product.dto;

import lombok.Data;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String imageUrl;
    private Long storeId;
    private String storeName;
    private LocalDateTime createdAt;
    //private LocalDateTime updatedAt;
}
