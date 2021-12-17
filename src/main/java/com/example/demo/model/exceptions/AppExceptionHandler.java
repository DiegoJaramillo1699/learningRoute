package com.example.demo.model.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ResponseBody
    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleException(EmptyResultDataAccessException exception) {

        return new ResponseEntity<>("No se encontró el cliente con el id indicado.",HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleException(MethodArgumentTypeMismatchException exception) {

        return new ResponseEntity<>("El atributo indicado no es el apropiado para la solicitud.",HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleException(MissingServletRequestParameterException exception) {

        return new ResponseEntity<>("La solicitud debe tener el atributo correspondiente para realizarse.",HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = ImagenNoEncontrada.class)
    public ResponseEntity<String> handleException(ImagenNoEncontrada exception) {

        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = ImagenIncompletaException.class)
    public ResponseEntity<String> handleException(ImagenIncompletaException exception) {

        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}