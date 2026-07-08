package com.minishopify.auth.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder

public class LoginResponse {
    private long id;
    private String fullName;
    private String email;
    private String role;
    private String message;
    

}
