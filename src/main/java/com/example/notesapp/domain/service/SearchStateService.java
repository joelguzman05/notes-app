package com.example.notesapp.domain.service;

import com.example.notesapp.presentation.request.dto.SearchRequest;
import com.example.notesapp.presentation.response.dto.SearchStateResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface SearchStateService {

    void createSearchState(SearchRequest searchRequest, UserDetails userDetails);

    List<SearchStateResponse> getSearchStates(UserDetails userDetails);

}
