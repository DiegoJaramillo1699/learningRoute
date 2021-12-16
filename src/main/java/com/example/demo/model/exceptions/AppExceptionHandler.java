package com.example.demo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException exception) {
       // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getFieldError().getDefaultMessage());
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors,
                HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleException(SQLIntegrityConstraintViolationException exception) {

        return new ResponseEntity<>("Ya existe un cliente registrado con este documento",HttpStatus.BAD_REQUEST);
    }
}