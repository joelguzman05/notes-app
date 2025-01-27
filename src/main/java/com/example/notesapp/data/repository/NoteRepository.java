package com.example.notesapp.data.repository;

import com.example.notesapp.domain.entity.Note;
import com.example.notesapp.domain.entity.Tag;
import com.example.notesapp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);

    Optional<Note> findByIdAndUser(Long id, User user);

    List<Note> findByTagsContainingAndUser(Tag tag, User user);

    @Query("SELECT n FROM Note n WHERE n.user = :user " +
            "AND (:query IS NULL OR :query = '' " +
            "OR (LOWER(n.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(n.content) LIKE LOWER(CONCAT('%', :query, '%')))) " +
            "AND (:tagIds IS NULL OR n IN (SELECT t.notes FROM Tag t WHERE t.id IN :tagIds)) " +
            "AND (:archived IS NULL OR n.archived = :archived)")
    List<Note> advancedSearch(
            @Param("user") User user,
            @Param("query") String query,
            @Param("tagIds") List<Long> tagIds,
            @Param("archived") Boolean archived
    );
}