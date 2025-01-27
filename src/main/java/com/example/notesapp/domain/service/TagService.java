package com.example.notesapp.domain.service;

import com.example.notesapp.presentation.request.dto.TagRequest;
import com.example.notesapp.presentation.response.dto.TagResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface TagService {

    TagResponse createTag(TagRequest tagRequest, UserDetails userDetails);

    List<TagResponse> getTags(UserDetails userDetails);

    TagResponse getTagById(Long id, UserDetails userDetails);

    TagResponse updateTag(Long id, TagRequest tagRequest, UserDetails userDetails);

    void deleteTag(Long id, Long replacementTagId, UserDetails userDetails);
}
