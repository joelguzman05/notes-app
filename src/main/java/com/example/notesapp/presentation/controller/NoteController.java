package com.example.notesapp.presentation.controller;

import com.example.notesapp.domain.service.NoteService;
import com.example.notesapp.presentation.request.dto.NoteRequest;
import com.example.notesapp.presentation.request.dto.SearchRequest;
import com.example.notesapp.presentation.response.dto.NoteResponse;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@Tag(name = "Notes", description = "API for managing user notes")
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    @Operation(
            summary = "Create a new note",
            description = "Creates a new note for the authenticated user with tags."
    )
    public ResponseEntity<NoteResponse> createNote(
            @Valid @RequestBody NoteRequest noteRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(noteRequest, userDetails));
    }

    @GetMapping
    @Operation(
            summary = "List all user notes",
            description = "Retrieves all notes for the authenticated user with tags."
    )
    public ResponseEntity<List<NoteResponse>> getNotes(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getNotes(userDetails));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a specific note",
            description = "Fetches the details of a specific note by ID for the authenticated user."
    )
    public ResponseEntity<NoteResponse> getNoteById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getNoteById(id, userDetails));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing note",
            description = "Updates the title and content of an existing note for the authenticated user."
    )
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest noteRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.updateNote(id, noteRequest, userDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a note",
            description = "Deletes a specific note by ID for the authenticated user."
    )
    public ResponseEntity<Void> deleteNote(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        noteService.deleteNote(id, userDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/archive")
    @Operation(
            summary = "Archive a note",
            description = "Archives a specific note by ID for the authenticated user."
    )
    public ResponseEntity<NoteResponse> archiveNote(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.archiveNote(id, userDetails));
    }

    @PatchMapping("/{id}/unarchive")
    @Operation(
            summary = "Unarchive a note",
            description = "Unarchives a specific note by ID for the authenticated user."
    )
    public ResponseEntity<NoteResponse> unarchiveNote(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.unarchiveNote(id, userDetails));
    }

    @PostMapping("/search")
    @Operation(
            summary = "Advanced Search",
            description = "Search notes for the authenticated user by title, content, tags or archived status."
    )
    public ResponseEntity<List<NoteResponse>> searchNotes(
            @Valid @RequestBody SearchRequest searchRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.advancedSearch(searchRequest, userDetails));
    }
}