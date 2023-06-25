package com.example.pharma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiResponse>resourceNotFoundHandler(ResourceNotFoundException ex){
        String msg=ex.getMessage();
        ApiResponse response=ApiResponse
                .builder()
                .message(msg)
                .success(true)
                .build();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(CannotUpdateDate.class)
    ResponseEntity<ApiResponse>cannotUpdateDateHandler(CannotUpdateDate ex){
        String msg=ex.getMessage();
        ApiResponse response=ApiResponse
                .builder()
                .message(msg)
                .success(false)
                .build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
    }
}