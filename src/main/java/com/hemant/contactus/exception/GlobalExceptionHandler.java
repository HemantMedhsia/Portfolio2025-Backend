package com.hemant.contactus.exception;

import com.hemant.contactus.dto.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleException(Exception e) {
        return ApiResponse.builder()
                .status("error")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
