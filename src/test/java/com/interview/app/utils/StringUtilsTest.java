package com.interview.app.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    private static Stream<Arguments> provideDataForIsNumberTest() {
        return Stream.of(
                Arguments.of("123", true),
                Arguments.of("asd", false),
                Arguments.of("1s1", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForIsNumberTest")
    void isNumberShouldReturnProperResponse(String input, Boolean expected) {
        //when
        Boolean actual = StringUtils.isNumber(input);
        //then
        assertThat(actual).isEqualTo(expected);
    }
}