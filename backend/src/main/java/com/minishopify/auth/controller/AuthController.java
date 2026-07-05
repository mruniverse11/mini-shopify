package com.minishopify.auth.controller;

import com.minishopify.auth.dto.RegisterRequest;
import com.minishopify.auth.dto.RegisterResponse;
import com.minishopify.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}