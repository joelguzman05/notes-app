package com.example.notesapp.domain.mapper;

import com.example.notesapp.domain.entity.Tag;
import com.example.notesapp.presentation.response.dto.TagResponse;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagResponse toTagResponse(Tag tag) {
        return new TagResponse(tag.getId(), tag.getName());
    }
}
