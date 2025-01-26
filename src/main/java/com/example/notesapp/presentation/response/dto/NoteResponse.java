package com.example.notesapp.presentation.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NoteResponse {

    private Long id;
    private String title;
    private String content;
    private List<TagResponse> tags;
}