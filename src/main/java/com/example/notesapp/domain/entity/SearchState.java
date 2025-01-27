package com.example.notesapp.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "search_states")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String searchQuery;

    @ElementCollection
    @CollectionTable(name = "search_tags", joinColumns = @JoinColumn(name = "search_state_id"))
    @Column(name = "tag_id")
    @Builder.Default
    private List<Long> tagIds = new ArrayList<>();

    @Column
    private Boolean archived;

    @Column(nullable = false)
    private LocalDateTime savedAt;
}
