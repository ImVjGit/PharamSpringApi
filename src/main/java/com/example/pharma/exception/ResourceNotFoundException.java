package com.example.pharma.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
