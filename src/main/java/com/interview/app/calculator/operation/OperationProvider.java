package com.interview.app.calculator.operation;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class OperationProvider {

    private final Map<Character, Operation> operations;

    public OperationProvider() {
        operations = Map.of('+', new PlusOperation(),
                '-', new MinusOperation(),
                '*', new MultiplyOperation(),
                '/', new DivideOperation());
    }

    public Operation getOperation(Character operation) {
        return Optional.ofNullable(operations.get(operation))
                .orElseThrow(() -> new UnsupportedOperationException("Unsupported math operation: " + operation));
    }

    public boolean isSupportedOpt(Character operation) {
        return operations.containsKey(operation);
    }
}
