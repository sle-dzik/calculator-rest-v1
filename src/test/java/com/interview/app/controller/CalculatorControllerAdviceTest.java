package com.interview.app.controller;

import com.interview.app.dto.CalculationResult;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static com.interview.app.controller.CalculatorControllerAdvice.VALIDATION_ERROR_DEFAULT_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculatorControllerAdviceTest {

    private final CalculatorControllerAdvice calculatorControllerAdvice = new CalculatorControllerAdvice();

    @Test
    void handleIllegalArgumentExceptionShouldReturnExceptionMessage() {
        //given
        String exceptionMessage = "Wrong data";
        IllegalArgumentException exception = new IllegalArgumentException(exceptionMessage);

        //when
        ResponseEntity<CalculationResult> responseEntity = calculatorControllerAdvice.handleIllegalArgumentException(exception);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        CalculationResult body = responseEntity.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getMessage()).isEqualTo(exceptionMessage);
        assertThat(body.getError()).isTrue();
        assertThat(body.getResult()).isNull();
    }

    @Test
    void handleArithmeticExceptionShouldReturnExceptionMessage() {
        //given
        String exceptionMessage = "Wrong data";
        ArithmeticException exception = new ArithmeticException(exceptionMessage);

        //when
        ResponseEntity<CalculationResult> responseEntity = calculatorControllerAdvice.handleArithmeticException(exception);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        CalculationResult body = responseEntity.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getMessage()).isEqualTo(exceptionMessage);
        assertThat(body.getError()).isTrue();
        assertThat(body.getResult()).isNull();
    }

    @Test
    void handleValidationExceptionShouldReturnDefMessageWhenNoConstraints() {
        //given
        ConstraintViolationException exception = new ConstraintViolationException(Set.of());

        //when
        ResponseEntity<CalculationResult> responseEntity = calculatorControllerAdvice.handleValidationException(exception);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        CalculationResult body = responseEntity.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getMessage()).isEqualTo(VALIDATION_ERROR_DEFAULT_MESSAGE);
        assertThat(body.getError()).isTrue();
        assertThat(body.getResult()).isNull();
    }

    @Test
    void handleValidationExceptionShouldReturnAnyOfConstraintMessage() {
        //given
        String exceptionMessage1 = "Wrong parameter 1";
        ConstraintViolation<String> constraintViolation1 = mock(ConstraintViolation.class);
        when(constraintViolation1.getMessage()).thenReturn(exceptionMessage1);

        String exceptionMessage2 = "Wrong parameter 2";
        ConstraintViolation<String> constraintViolation2 = mock(ConstraintViolation.class);
        when(constraintViolation2.getMessage()).thenReturn(exceptionMessage2);


        ConstraintViolationException exception = new ConstraintViolationException(Set.of(constraintViolation1, constraintViolation2));

        //when
        ResponseEntity<CalculationResult> responseEntity = calculatorControllerAdvice.handleValidationException(exception);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        CalculationResult body = responseEntity.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getMessage()).containsAnyOf(exceptionMessage1, exceptionMessage2);
        assertThat(body.getError()).isTrue();
        assertThat(body.getResult()).isNull();
    }

}