package com.example.notesapp.presentation.controller;

import com.example.notesapp.domain.service.TagService;
import com.example.notesapp.presentation.request.dto.TagRequest;
import com.example.notesapp.presentation.response.dto.TagResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@Tag(name = "Tags", description = "API for managing user tags")
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping
    @Operation(
            summary = "Create a new tag",
            description = "Creates a new tag for the authenticated user."
    )
    public ResponseEntity<TagResponse> createTag(
            @Valid @RequestBody TagRequest tagRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.createTag(tagRequest, userDetails));
    }

    @GetMapping
    @Operation(
            summary = "List all user tags",
            description = "Retrieves all tags for the authenticated user."
    )
    public ResponseEntity<List<TagResponse>> getTags(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.getTags(userDetails));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a specific tag",
            description = "Fetches a specific tag by ID for the authenticated user."
    )
    public ResponseEntity<TagResponse> getTagById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.getTagById(id, userDetails));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing tag",
            description = "Updates the name of an existing tag for the authenticated user."
    )
    public ResponseEntity<TagResponse> updateTag(
            @PathVariable Long id,
            @Valid @RequestBody TagRequest tagRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.updateTag(id, tagRequest, userDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a tag",
            description = "Deletes a specific tag by ID for the authenticated user, optionally reassigning its notes to another tag."
    )
    public ResponseEntity<Void> deleteTag(
            @PathVariable Long id,
            @RequestParam(value = "replacementTagId", required = false) Long replacementTagId,
            @AuthenticationPrincipal UserDetails userDetails) {
        tagService.deleteTag(id, replacementTagId, userDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}