package com.example.notesapp.presentation.controller;

import com.example.notesapp.domain.service.AuthService;
import com.example.notesapp.presentation.request.dto.LoginRequest;
import com.example.notesapp.presentation.request.dto.RegisterRequest;
import com.example.notesapp.presentation.response.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}