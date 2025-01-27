package com.example.notesapp.presentation.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SearchStateResponse {

    private Long id;
    private String searchQuery;
    private List<Long> tagIds;
    private Boolean archived;
    private LocalDateTime savedAt;
}
