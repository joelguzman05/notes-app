package com.example.notesapp.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(Long id) {
        super("Note with ID " + id + " not found");
    }
}