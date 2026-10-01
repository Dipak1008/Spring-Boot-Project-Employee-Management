package com.springboot.basic.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GolbalExceptionHandler {

	// Field validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleFieldValidations(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
            .forEach(error -> {
                errors.put(
                    error.getField(),
                    error.getDefaultMessage()
                );
            });

        return errors;
    }
    
    
    // Employee not found exception
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(EmployeeNotFoundException ex) {
		return ex.getMessage();
		
		   	
    }
    
    
    //Duplicate Employee
    @ExceptionHandler(DuplicateEmployeeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDuplicateException(DuplicateEmployeeException ex) {
    	return ex.getMessage();
    }
    
    
    //General exception
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
		return ex.getMessage();
    	
    }
    
    
    // Any Other exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex) {
		return "Something went wrong: " + ex.getMessage();
    	
    }
}
