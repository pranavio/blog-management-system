package com.example.blogkar.exception;

import org.springframework.validation.Errors;

public record MethodArgumentNotValidException() {
    public Errors getBindingResult() {
        return null;
    }
}
