package com.example.notesapp.security;

import com.example.notesapp.domain.entity.User;
import com.example.notesapp.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserContext {

    private final UserService userService;

    public User getCurrentUser(UserDetails userDetails) {
        return userService.findByUsername(userDetails.getUsername());
    }
}