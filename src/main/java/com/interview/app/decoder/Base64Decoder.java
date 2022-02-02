package com.interview.app.decoder;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Component
class Base64Decoder implements Decoder {

    @Override
    public String decode(String input) {
        validateInput(input);
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(String.format("Unable to decode base64 value:[%s]", input), ex);
        }
        return new String(decoded, StandardCharsets.UTF_8);
    }

    private void validateInput(String input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("Base64 value cannot be null");
        }
    }
}
