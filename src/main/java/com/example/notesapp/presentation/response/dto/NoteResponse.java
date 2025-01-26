package com.example.notesapp.presentation.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoteResponse {

    private Long id;
    private String title;
    private String content;
}