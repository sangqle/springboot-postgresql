package com.cabin.example.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
