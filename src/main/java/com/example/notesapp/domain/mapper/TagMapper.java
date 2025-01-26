package com.example.notesapp.domain.mapper;

import com.example.notesapp.domain.entity.Tag;
import com.example.notesapp.domain.entity.User;
import com.example.notesapp.presentation.request.dto.TagRequest;
import com.example.notesapp.presentation.response.dto.TagResponse;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagResponse toTagResponse(Tag tag) {
        return new TagResponse(tag.getId(), tag.getName());
    }

    public Tag toEntity(TagRequest request, User user) {
        return Tag.builder()
                .name(request.getName())
                .user(user)
                .build();
    }
}
