package com.interview.app.calculator;

import com.interview.app.calculator.operation.OperationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUP() {
        calculator = new Calculator(new ExpressionSplitter(), new OperationProvider());
    }

    private static Stream<Arguments> provideCorrectCalculationData() {
        return Stream.of(
                Arguments.of("1", 1.0),
                Arguments.of("1+2", 3.0),
                Arguments.of("2-1", 1.0),
                Arguments.of("1*2", 2.0),
                Arguments.of("6/2", 3.0),
                Arguments.of("2*2+3", 7.0),
                Arguments.of("3+2*2", 7.0),
                Arguments.of("3+6/3", 5.0),
                Arguments.of("(1+2)*3", 9.0),
                Arguments.of("3/(1+2)", 1.0),
                Arguments.of("(1*2+3)*3", 15.0),
                Arguments.of("(1+2)*3+1", 10.0),
                Arguments.of("(27/(3*3))", 3.0),
                Arguments.of("(27-9/(3*3))", 26.0),
                Arguments.of("(27-(9/(3*3)))", 26.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCorrectCalculationData")
    void calculateShouldReturnSuccessResult(String input, Double expected) {
        //when
        Double actual = calculator.calculate(input);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideWrongCalculationData() {
        return Stream.of(
                Arguments.of("-1", "Invalid expression"),
                Arguments.of("(1+2", "Missing parenthesis"),
                Arguments.of("1+2)", "Missing parenthesis"),
                Arguments.of("1.0+2)", "Not supported expression element: ."),
                Arguments.of("((10+2)", "Missing parenthesis"),
                Arguments.of("((1.0+2)", "Not supported expression element: ."),
                Arguments.of("1/0", "Divide by 0 is prohibited")

        );
    }

    @ParameterizedTest
    @MethodSource("provideWrongCalculationData")
    void calculateShouldReturnExceptionResult(String input, String expectedMessage) {
        //when&&then
        RuntimeException actualException = assertThrows(RuntimeException.class, () -> calculator.calculate(input));
        assertThat(actualException.getMessage()).isEqualTo(expectedMessage);
    }
}
