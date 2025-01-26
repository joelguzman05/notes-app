package com.example.notesapp.domain.mapper;

import com.example.notesapp.domain.entity.Note;
import com.example.notesapp.domain.entity.User;
import com.example.notesapp.presentation.request.dto.NoteRequest;
import com.example.notesapp.presentation.response.dto.NoteResponse;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public NoteResponse toNoteResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent()
        );
    }

    public Note toEntity(NoteRequest request, User user) {
        return Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();
    }
}