package com.interview.app.controller;

import com.interview.app.dto.CalculationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CalculatorControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<CalculationResult> handleIllegalArgumentException(IllegalArgumentException ex) {
        CalculationResult build = CalculationResult.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CalculationResult> handleValidationExceptions(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream().findFirst().get().getMessage();
        CalculationResult build = CalculationResult.builder().message(message).build();
        return new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
    }
}