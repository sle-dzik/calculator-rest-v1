package com.interview.app.calculator.operation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MinusOperationTest {

    private final Operation minusOperation = new MinusOperation();

    @Test
    void minusOperationShouldReturnCorrectResult() {
        //given
        Double firstValue = 6.0;
        Double secondValue = 2.0;
        Double expected = 4.0;

        //when
        Double result = minusOperation.calculate(firstValue, secondValue);

        //then
        assertThat(result).isEqualTo(expected);
    }
}