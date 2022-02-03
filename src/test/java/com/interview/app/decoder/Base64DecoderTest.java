package com.interview.app.decoder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class Base64DecoderTest {

    private final Decoder decoder = new Base64Decoder();

    @Test
    void decode_ShouldReturnIllegalArgumentExceptionWhenNullString() {
        //given
        String input = null;

        //when && then
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> decoder.decode(input));
        assertThat(actualException.getMessage()).isEqualTo("Base64 value cannot be null");
    }

    @Test
    void decode_ShouldReturnIllegalArgumentExceptionWhenIncorrectBase64() {
        //given
        String input = "123==";

        //when && then
        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> decoder.decode(input));
        assertThat(actualException.getMessage()).isEqualTo("Unable to decode base64 value:[123==]");
    }

    @Test
    void decode_ShouldReturnSuccessfullyDecodeBase64() {
        //given
        String input = "KDIzLygzKjMpKQ==";
        String expected = "(23/(3*3))";

        //when
        String actual = decoder.decode(input);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}

