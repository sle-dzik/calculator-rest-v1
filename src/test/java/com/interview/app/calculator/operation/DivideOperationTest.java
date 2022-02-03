package com.interview.app.calculator.operation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivideOperationTest {

    private final Operation divideOperation = new DivideOperation();

    @Test
    void divideOperationShouldReturnCorrectResult() {
        //given
        Double firstValue = 6.0;
        Double secondValue = 2.0;
        Double expected = 3.0;

        //when
        Double result = divideOperation.calculate(firstValue, secondValue);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void divideOperationShouldReturnArithmeticExceptionWhenDivideBy0() {
        //given
        Double firstValue = 6.0;
        Double secondValue = 0.0;

        //when
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> divideOperation.calculate(firstValue, secondValue));

        //when
        Assertions.assertThat(actualException.getMessage()).isEqualTo("Divide by 0 is prohibited");
    }

}