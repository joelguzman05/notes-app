package com.example.notesapp.presentation.request.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequest {

    @Size(max = 255, message = "Search query must not exceed 255 characters")
    private String searchQuery;

    @Size(max = 10, message = "A maximum of 10 tags can be selected for searching")
    private List<Long> tagIds;

    private Boolean archived;
}
