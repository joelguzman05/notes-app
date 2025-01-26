package com.example.notesapp.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TagAlreadyExistsException extends RuntimeException {

    public TagAlreadyExistsException(String name) {
        super("Tag '" + name + "' already exists");
    }
}