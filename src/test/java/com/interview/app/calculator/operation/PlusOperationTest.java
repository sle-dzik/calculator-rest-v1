package com.interview.app.calculator.operation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlusOperationTest {

    private final Operation plusOperation = new PlusOperation();

    @Test
    void plusOperationShouldReturnCorrectResult() {
        //given
        Double firstValue = 3.0;
        Double secondValue = 2.0;
        Double expected = 5.0;

        //when
        Double result = plusOperation.calculate(firstValue, secondValue);

        //then
        assertThat(result).isEqualTo(expected);
    }
}