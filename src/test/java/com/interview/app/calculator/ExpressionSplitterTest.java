package com.interview.app.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ExpressionSplitterTest {

    private final ExpressionSplitter expressionSplitter = new ExpressionSplitter();

    private static Stream<Arguments> provideExpressions() {
        return Stream.of(
                Arguments.of("", List.of()),
                Arguments.of(" 0 ", List.of("0")),
                Arguments.of("1 1", List.of("11")),
                Arguments.of("1+2", List.of("1", "+", "2")),
                Arguments.of("(13+29)", List.of("(", "13", "+", "29", ")"))
        );
    }


    @ParameterizedTest
    @MethodSource("provideExpressions")
    void split_ShouldReturnSplittedExpresion(String input, List<String> expected) {
        //when
        List<String> actual = expressionSplitter.split(input);
        //then
        assertThat(actual).containsExactlyElementsOf(expected);
    }

}