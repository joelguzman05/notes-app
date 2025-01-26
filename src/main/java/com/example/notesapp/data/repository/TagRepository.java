package com.example.notesapp.data.repository;

import com.example.notesapp.domain.entity.Tag;
import com.example.notesapp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByUser(User user);

    Optional<Tag> findByIdAndUser(Long id, User user);

    boolean existsByNameAndUser(String name, User user);

    List<Tag> findAllByIdInAndUser(List<Long> ids, User user);
}