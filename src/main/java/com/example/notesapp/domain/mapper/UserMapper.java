package com.example.notesapp.domain.mapper;

import com.example.notesapp.domain.entity.User;
import com.example.notesapp.presentation.response.dto.UserProfileResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserProfileResponse toUserProfileResponse(User user) {
        return new UserProfileResponse(user.getId(), user.getUsername());
    }
}
