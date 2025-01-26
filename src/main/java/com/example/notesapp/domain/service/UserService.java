package com.example.notesapp.domain.service;

import com.example.notesapp.domain.entity.User;
import com.example.notesapp.presentation.response.dto.UserProfileResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserProfileResponse getAuthenticatedUser(UserDetails userDetails);

    User findByUsername(String username);
}
