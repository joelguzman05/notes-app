package com.example.notesapp.domain.service;

import com.example.notesapp.presentation.request.dto.LoginRequest;
import com.example.notesapp.presentation.request.dto.RegisterRequest;
import com.example.notesapp.presentation.response.dto.AuthResponse;

public interface AuthService {

    void register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);
}
