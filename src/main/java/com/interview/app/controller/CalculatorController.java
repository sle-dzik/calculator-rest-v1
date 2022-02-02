package com.interview.app.controller;

import com.interview.app.decoder.DecodeService;
import com.interview.app.dto.CalculationResult;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@Validated
@RestController
@AllArgsConstructor
public class CalculatorController {

    private DecodeService decodeService;

    @GetMapping(value = "/calculus", produces = MediaType.APPLICATION_JSON_VALUE)
    public CalculationResult calculate(@RequestParam @NotEmpty(message = "Request query cannot be empty") String query) {
        String decodedQuery = decodeService.decodeBase64(query);
        return CalculationResult.builder().message(decodedQuery).build();
    }
}
