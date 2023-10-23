package com.example.studychat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class StudyChatExceptionHandler {

    @ExceptionHandler(StudyChatException.class)
    public ResponseEntity<String> handleStudyChatException(StudyChatException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
