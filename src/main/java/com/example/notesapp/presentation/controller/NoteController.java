package com.example.notesapp.presentation.controller;

import com.example.notesapp.domain.service.NoteService;
import com.example.notesapp.presentation.request.dto.NoteRequest;
import com.example.notesapp.presentation.response.dto.NoteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
            description = "Creates a new note for the authenticated user."
    )
    public ResponseEntity<NoteResponse> createNote(
            @RequestBody NoteRequest noteRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(noteRequest, userDetails));
    }

    @GetMapping
    @Operation(
            summary = "List all user notes",
            description = "Retrieves all notes for the authenticated user."
    )
    public ResponseEntity<List<NoteResponse>> getUserNotes(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getUserNotes(userDetails));
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
            @RequestBody NoteRequest noteRequest,
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
}