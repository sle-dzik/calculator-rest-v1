package com.interview.app.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalculationResult {

    private boolean error;
    private Double result;
    private String message;
}
