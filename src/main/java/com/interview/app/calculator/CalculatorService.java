package com.interview.app.calculator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class CalculatorService {

    private Calculator calculator;

    public Double calculate(String expression) {
        Double result = calculator.calculate(expression);
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
