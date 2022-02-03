package com.interview.app.calculator.operation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MultiplyOperationTest {

    private final Operation multiplyOperation = new MultiplyOperation();

    @Test
    void multiplyOperationShouldReturnCorrectResult() {
        //given
        Double firstValue = 3.0;
        Double secondValue = 2.0;
        Double expected = 6.0;

        //when
        Double result = multiplyOperation.calculate(firstValue, secondValue);

        //then
        assertThat(result).isEqualTo(expected);
    }
}