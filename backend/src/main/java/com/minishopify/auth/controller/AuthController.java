package com.minishopify.auth.controller;

import com.minishopify.auth.dto.RegisterRequest;
import com.minishopify.auth.dto.RegisterResponse;
import com.minishopify.auth.service.AuthService;

import com.minishopify.auth.dto.LoginRequest;
import com.minishopify.auth.dto.LoginResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}