package com.example.notesapp.data.repository;

import com.example.notesapp.domain.entity.SearchState;
import com.example.notesapp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchStateRepository extends JpaRepository<SearchState, Long> {

    List<SearchState> findByUser(User user);
}
