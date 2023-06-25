package com.example.pharma.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
}
