package com.example.notesapp.domain.service;

import com.example.notesapp.data.repository.SearchStateRepository;
import com.example.notesapp.domain.entity.SearchState;
import com.example.notesapp.domain.entity.User;
import com.example.notesapp.domain.mapper.SearchStateMapper;
import com.example.notesapp.presentation.request.dto.SearchRequest;
import com.example.notesapp.presentation.response.dto.SearchStateResponse;
import com.example.notesapp.security.UserContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchStateServiceImpl implements SearchStateService {

    private final SearchStateRepository searchStateRepository;
    private final SearchStateMapper searchStateMapper;
    private final UserContext userContext;

    @Override
    public void createSearchState(SearchRequest searchRequest, UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);

        SearchState searchState = SearchState.builder()
                .user(user)
                .searchQuery(searchRequest.getSearchQuery())
                .tagIds(searchRequest.getTagIds())
                .archived(searchRequest.getArchived())
                .savedAt(LocalDateTime.now())
                .build();
        searchStateRepository.save(searchState);
    }

    @Override
    public List<SearchStateResponse> getSearchStates(UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        return searchStateRepository.findByUser(user).stream()
                .map(searchStateMapper::toSearchStateResponse)
                .collect(Collectors.toList());
    }
}
