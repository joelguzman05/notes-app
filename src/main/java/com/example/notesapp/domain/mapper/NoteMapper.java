package com.example.notesapp.domain.mapper;

import com.example.notesapp.domain.entity.Note;
import com.example.notesapp.presentation.response.dto.NoteResponse;
import com.example.notesapp.presentation.response.dto.TagResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NoteMapper {

    private final TagMapper tagMapper;

    public NoteResponse toNoteResponse(Note note) {
        List<TagResponse> tags = note.getTags().stream()
                .map(tagMapper::toTagResponse)
                .toList();

        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.isArchived(),
                tags
        );
    }
}