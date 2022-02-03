package com.interview.app.decoder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DecodeService {

    private Decoder base64Decoder;

    public String decodeBase64(String input) {
        return base64Decoder.decode(input);
    }
}
