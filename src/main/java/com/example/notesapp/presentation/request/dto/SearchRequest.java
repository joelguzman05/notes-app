package com.example.notesapp.presentation.request.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequest {

    @Size(max = 255, message = "Tag name must not exceed 255 characters")
    private String searchQuery;

    private List<Long> tagIds;

    private Boolean archived;
}
