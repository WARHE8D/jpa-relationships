package com.warhe8d.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MainException {

    @ExceptionHandler(LibraryExceptions.class)
    public ResponseEntity<ErrorResponse> relationException(LibraryExceptions e) {
        ErrorResponse res = new ErrorResponse(
                LocalDateTime.now(),
                e.getMessage()
        );
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
