package com.interview.app.calculator.operation;

public enum OperationPriority {
    HIGH(2), LOW(1);

    private final int value;

    OperationPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
