package com.example.notesapp.domain.service;

import com.example.notesapp.data.repository.NoteRepository;
import com.example.notesapp.domain.entity.Note;
import com.example.notesapp.domain.entity.User;
import com.example.notesapp.domain.mapper.NoteMapper;
import com.example.notesapp.exception.custom.NoteNotFoundException;
import com.example.notesapp.presentation.request.dto.NoteRequest;
import com.example.notesapp.presentation.response.dto.NoteResponse;
import com.example.notesapp.security.UserContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final UserContext userContext;

    @Override
    public NoteResponse createNote(NoteRequest noteRequest, UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        Note note = noteMapper.toEntity(noteRequest, user);
        Note savedNote = noteRepository.save(note);
        return noteMapper.toNoteResponse(savedNote);
    }

    @Override
    public List<NoteResponse> getUserNotes(UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        return noteRepository.findByUser(user).stream()
                .map(noteMapper::toNoteResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NoteResponse getNoteById(Long id, UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        Note note = noteRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new NoteNotFoundException(id));
        return noteMapper.toNoteResponse(note);
    }

    @Override
    public NoteResponse updateNote(Long id, NoteRequest noteRequest, UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        Note existingNote = noteRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new NoteNotFoundException(id));

        existingNote.setTitle(noteRequest.getTitle());
        existingNote.setContent(noteRequest.getContent());

        Note updatedNote = noteRepository.save(existingNote);
        return noteMapper.toNoteResponse(updatedNote);
    }

    @Override
    public void deleteNote(Long id, UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        Note note = noteRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new NoteNotFoundException(id));

        noteRepository.delete(note);
    }
}
