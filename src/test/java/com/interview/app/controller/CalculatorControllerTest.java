package com.interview.app.controller;

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

    @BeforeEach
    void setUp() {
        calculatorController = new CalculatorController(decodeService);
    }

    @Test
    void calculate_ShouldReturnDecodedString() {
        //given
        String query = "testQuery";
        String expectedResult = "expectedResult";
        when(decodeService.decodeBase64(eq(query))).thenReturn(expectedResult);

        //when
        CalculationResult result = calculatorController.calculate(query);

        //then
        assertThat(result.getMessage()).isEqualTo(expectedResult);
        verify(decodeService).decodeBase64(eq(query));
    }
}