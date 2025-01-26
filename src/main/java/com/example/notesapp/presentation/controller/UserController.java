package com.example.notesapp.presentation.controller;

import com.example.notesapp.domain.service.UserService;
import com.example.notesapp.presentation.response.dto.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "User", description = "User profile management API")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(
            summary = "Get authenticated user",
            description = "Returns the currently authenticated user's profile.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    public ResponseEntity<UserProfileResponse> getAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserProfileResponse user = userService.getAuthenticatedUser(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}