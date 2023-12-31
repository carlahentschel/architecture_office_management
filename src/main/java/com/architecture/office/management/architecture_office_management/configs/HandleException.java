package com.architecture.office.management.architecture_office_management.configs;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity dataValidationError(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors().stream().map(ErrorData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    public record ErrorData(String field, String message){
        public ErrorData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
