package com.example.notesapp.domain.mapper;

import com.example.notesapp.domain.entity.SearchState;
import com.example.notesapp.presentation.response.dto.SearchStateResponse;
import org.springframework.stereotype.Component;

@Component
public class SearchStateMapper {

    public SearchStateResponse toSearchStateResponse(SearchState searchState) {
        return new SearchStateResponse(
                searchState.getId(),
                searchState.getSearchQuery(),
                searchState.getTagIds(),
                searchState.getArchived(),
                searchState.getSavedAt()
        );
    }
}
