package com.example.notesapp.domain.service;

import com.example.notesapp.data.repository.NoteRepository;
import com.example.notesapp.data.repository.TagRepository;
import com.example.notesapp.domain.entity.Note;
import com.example.notesapp.domain.entity.Tag;
import com.example.notesapp.domain.entity.User;
import com.example.notesapp.domain.mapper.TagMapper;
import com.example.notesapp.exception.custom.TagAlreadyExistsException;
import com.example.notesapp.exception.custom.TagNotFoundException;
import com.example.notesapp.presentation.request.dto.TagRequest;
import com.example.notesapp.presentation.response.dto.TagResponse;
import com.example.notesapp.security.UserContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final UserContext userContext;
    private final NoteRepository noteRepository;

    @Override
    public TagResponse createTag(TagRequest tagRequest, UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        if (tagRepository.existsByNameAndUser(tagRequest.getName(), user)) {
            throw new TagAlreadyExistsException(tagRequest.getName());
        }

        Tag tag = Tag.builder()
                .name(tagRequest.getName())
                .user(user)
                .build();

        Tag savedTag = tagRepository.save(tag);
        return tagMapper.toTagResponse(savedTag);
    }

    @Override
    public List<TagResponse> getUserTags(UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        return tagRepository.findByUser(user).stream()
                .map(tagMapper::toTagResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TagResponse getTagById(Long id, UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        Tag tag = findByIdAndUser(id, user);
        return tagMapper.toTagResponse(tag);
    }

    @Override
    public TagResponse updateTag(Long id, TagRequest tagRequest, UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        Tag existingTag = findByIdAndUser(id, user);

        existingTag.setName(tagRequest.getName());

        Tag updatedTag = tagRepository.save(existingTag);
        return tagMapper.toTagResponse(updatedTag);
    }

    @Override
    @Transactional
    public void deleteTag(Long id, Long replacementTagId, UserDetails userDetails) {
        User user = userContext.getCurrentUser(userDetails);
        Tag tagToDelete = findByIdAndUser(id, user);

        List<Note> notesToUpdate = noteRepository.findByTagsContainingAndUser(tagToDelete, user);
        if (replacementTagId != null) {
            Tag newTag = findByIdAndUser(replacementTagId, user);
            notesToUpdate.forEach(note -> note.getTags().add(newTag));
        }

        notesToUpdate.forEach(note -> note.getTags().remove(tagToDelete));
        noteRepository.saveAll(notesToUpdate);

        tagRepository.delete(tagToDelete);
    }

    private Tag findByIdAndUser(Long id, User user) {
        return tagRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TagNotFoundException(id));
    }
}
