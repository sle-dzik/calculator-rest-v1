package com.interview.app.calculator.operation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperationProviderTest {

    private final OperationProvider operationProvider = new OperationProvider();

    private static Stream<Arguments> provideIsSupportedTestCases() {
        return Stream.of(
                Arguments.of('+', true),
                Arguments.of('-', true),
                Arguments.of('*', true),
                Arguments.of('/', true),
                Arguments.of('^', false)
        );
    }


    @ParameterizedTest
    @MethodSource("provideIsSupportedTestCases")
    void isSupportedOpt_ShouldReturnExpectedBoolean(Character operationSymbol, Boolean expected) {
        //when
        Boolean actual = operationProvider.isSupportedOpt(operationSymbol);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testCasesForSupportedOperations() {
        return Stream.of(
                Arguments.of('+', PlusOperation.class),
                Arguments.of('-', MinusOperation.class),
                Arguments.of('*', MultiplyOperation.class),
                Arguments.of('/', DivideOperation.class)
        );
    }


    @ParameterizedTest
    @MethodSource("testCasesForSupportedOperations")
    void getOperation_ShouldReturnExpectedOperationClassInstance(Character operationSymbol, Class expected) {
        //when
        Operation actual = operationProvider.getOperation(operationSymbol);
        //then
        Class actualOperationClass = Optional.ofNullable(actual).map(Operation::getClass).orElse(null);
        assertThat(actualOperationClass).isEqualTo(expected);
    }

    @Test
    void getOperation_ShouldReturnUnsupportedExceptionForUnknownOperation() {
        //given
        char unsupportedOperation = '(';
        //when
        UnsupportedOperationException actualException = assertThrows(UnsupportedOperationException.class, () -> operationProvider.getOperation(unsupportedOperation));

        //when
        Assertions.assertThat(actualException.getMessage()).isEqualTo("Unsupported math operation: " + unsupportedOperation);
    }
}