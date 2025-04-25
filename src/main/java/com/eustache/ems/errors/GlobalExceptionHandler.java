package com.eustache.ems.errors;

import com.eustache.ems.mapper.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleEmployeeNotFound(ResourceNotFoundException ex) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exp) {
        //create a map to store field names and their corresponding error message
        var errors = new HashMap<String, String>();
        //Iterate through all validation errors and populate the map
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField(); //Extract the field name
            var errorMessage = error.getDefaultMessage(); //Extract the error message
            errors.put(fieldName, errorMessage);
        });
        //Return the map of errors wrapped in a ResponseEntity with a BAD_REQUEST status
        return ResponseEntity.badRequest().body(errors);
    }
}
