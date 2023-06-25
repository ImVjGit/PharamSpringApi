package com.example.pharma.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CannotUpdateDate extends RuntimeException {
    public CannotUpdateDate() {
    }

    public CannotUpdateDate(String message) {
        super(message);
    }
}
