package com.interview.app.controller;

import com.interview.app.calculator.CalculatorService;
import com.interview.app.decoder.DecodeService;
import com.interview.app.dto.CalculationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorControllerTest {

    private CalculatorController calculatorController;

    @Mock
    private DecodeService decodeService;
    @Mock
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorController = new CalculatorController(decodeService, calculatorService);
    }

    @Test
    void calculateShouldReturnDecodedString() {
        //given
        String encodedQuery = "encodedQuery";
        String decodedQuery = "decodedQuery";
        Double result = 9.99;
        when(decodeService.decodeBase64(eq(encodedQuery))).thenReturn(decodedQuery);
        when(calculatorService.calculate(eq(decodedQuery))).thenReturn(result);

        CalculationResult expectedResult = CalculationResult.builder().error(false).result(result).build();

        //when
        CalculationResult actualResult = calculatorController.calculate(encodedQuery);

        //then
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getResult()).isEqualTo(expectedResult.getResult());
        assertThat(actualResult.getMessage()).isEqualTo(expectedResult.getMessage());
        assertThat(actualResult.getError()).isEqualTo(expectedResult.getError());
        verify(decodeService).decodeBase64(eq(encodedQuery));
        verify(calculatorService).calculate(eq(decodedQuery));
    }
}