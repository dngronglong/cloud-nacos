package com.example.security.advice;

import com.example.security.controller.UserController;
import com.example.security.damain.ErrorDetails;
import com.example.security.exception.CustomizeException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice(assignableTypes = {UserController.class})
public class CustomControllerAdvice {
    @ExceptionHandler
    public HttpEntity customExceptionHandler(CustomizeException e, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorDetails.setMessage(e.getLocalizedMessage());
        errorDetails.setPath(request.getServletPath());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorDetails);
    }
}
