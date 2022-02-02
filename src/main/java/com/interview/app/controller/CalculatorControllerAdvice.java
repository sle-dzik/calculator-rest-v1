package com.interview.app.controller;

import com.interview.app.dto.CalculationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CalculatorControllerAdvice extends ResponseEntityExceptionHandler {

    public static String VALIDATION_ERROR_DEFAULT_MESSAGE = "Validation error";

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<CalculationResult> handleIllegalArgumentException(IllegalArgumentException ex) {
        CalculationResult build = buildErrorResponse(ex.getMessage());
        return new ResponseEntity<>(buildErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CalculationResult> handleValidationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse(VALIDATION_ERROR_DEFAULT_MESSAGE);

        return new ResponseEntity<>(buildErrorResponse(message), HttpStatus.BAD_REQUEST);
    }

    private CalculationResult buildErrorResponse(String message) {
        return CalculationResult.builder().error(true).message(message).build();
    }
}