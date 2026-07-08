package com.minishopify.store.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class StoreResponse {
    private long id;
    private String storeName;
    private String slug;
    private String description;
    private String ownerEmail;
    private String message;
}
