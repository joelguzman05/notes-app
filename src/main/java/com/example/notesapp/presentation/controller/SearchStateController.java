package com.example.notesapp.presentation.controller;

import com.example.notesapp.domain.service.SearchStateService;
import com.example.notesapp.presentation.response.dto.SearchStateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@Tag(name = "Search States", description = "API for managing user search states")
@RequestMapping("/search-states")
public class SearchStateController {

    private final SearchStateService searchStateService;

    @GetMapping
    @Operation(
            summary = "List all user search states",
            description = "Retrieves all search states for the authenticated user."
    )
    public ResponseEntity<List<SearchStateResponse>> getSearchStates(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<SearchStateResponse> searchStates = searchStateService.getSearchStates(userDetails);
        return ResponseEntity.ok(searchStates);
    }
}