package com.example.notesapp.domain.service;

import com.example.notesapp.data.repository.UserRepository;
import com.example.notesapp.domain.entity.User;
import com.example.notesapp.domain.mapper.UserMapper;
import com.example.notesapp.exception.custom.UserNotFoundException;
import com.example.notesapp.presentation.response.dto.UserProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserProfileResponse getAuthenticatedUser(UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return userMapper.toUserProfileResponse(user);
    }
}
