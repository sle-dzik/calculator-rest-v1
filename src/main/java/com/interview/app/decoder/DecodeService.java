package com.interview.app.decoder;

import org.springframework.stereotype.Service;

@Service
public class DecodeService {

    private Decoder base64Decoder;

    public DecodeService(Decoder base64Decoder) {
        this.base64Decoder = base64Decoder;
    }

    public String decodeBase64(String input) {
        return base64Decoder.decode(input);
    }
}
