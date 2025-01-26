package com.example.notesapp.domain.service;

import com.example.notesapp.data.repository.UserRepository;
import com.example.notesapp.domain.entity.User;
import com.example.notesapp.domain.mapper.UserMapper;
import com.example.notesapp.exception.custom.UsernameAlreadyExistsException;
import com.example.notesapp.presentation.request.dto.LoginRequest;
import com.example.notesapp.presentation.request.dto.RegisterRequest;
import com.example.notesapp.presentation.response.dto.AuthResponse;
import com.example.notesapp.presentation.response.dto.UserProfileResponse;
import com.example.notesapp.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public UserProfileResponse register(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(registerRequest.getUsername());
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(encodedPassword)
                .build();

        User savedUser = userRepository.save(user);
        return userMapper.toUserProfileResponse(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        String token = jwtUtil.generateToken(loginRequest.getUsername());
        return new AuthResponse(loginRequest.getUsername(), token);
    }
}