package com.minishopify.auth.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class RegisterResponse {
    private long id;
    private String fullName;
    private String email;
    private String role;
    private String message;
}
