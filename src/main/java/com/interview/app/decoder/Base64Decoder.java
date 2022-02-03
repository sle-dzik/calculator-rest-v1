package com.interview.app.decoder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Slf4j
@Component
class Base64Decoder implements Decoder {

    @Override
    public String decode(String input) {
        log.info("Decoding input:[{}]", input);
        validateInput(input);
        byte[] decodedArray;
        String decodedResult;
        try {
            decodedArray = Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8));
            decodedResult = new String(decodedArray);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(String.format("Unable to decode base64 value:[%s]", input), ex);
        }
        log.debug("Decoded result:[{}] for input:[{}]", decodedResult, input);
        return decodedResult;
    }

    private void validateInput(String input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("Base64 value cannot be null");
        }
    }
}
