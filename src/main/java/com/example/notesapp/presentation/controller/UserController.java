package com.example.notesapp.presentation.controller;

import com.example.notesapp.domain.service.UserService;
import com.example.notesapp.presentation.response.dto.UserProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserProfileResponse user = userService.getAuthenticatedUser(userDetails);
        return ResponseEntity.ok(user);
    }
}