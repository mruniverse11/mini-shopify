package com.minishopify.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class StoreRequest {
    @NotBlank(message = "Store name is required")
    private String storeName;

    @NotBlank(message = "Store description is required")
    private String description;                      
}
