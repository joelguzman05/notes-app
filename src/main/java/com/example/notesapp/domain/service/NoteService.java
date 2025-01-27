package com.example.notesapp.domain.service;

import com.example.notesapp.presentation.request.dto.NoteRequest;
import com.example.notesapp.presentation.request.dto.SearchRequest;
import com.example.notesapp.presentation.response.dto.NoteResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface NoteService {

    NoteResponse createNote(NoteRequest noteRequest, UserDetails userDetails);

    List<NoteResponse> getNotes(UserDetails userDetails);

    NoteResponse getNoteById(Long id, UserDetails userDetails);

    NoteResponse updateNote(Long id, NoteRequest noteRequest, UserDetails userDetails);

    void deleteNote(Long id, UserDetails userDetails);

    NoteResponse archiveNote(Long id, UserDetails userDetails);

    NoteResponse unarchiveNote(Long id, UserDetails userDetails);

    List<NoteResponse> advancedSearch(SearchRequest searchRequest, UserDetails userDetails);
}
