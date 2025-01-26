package com.example.notesapp.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GenericErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}