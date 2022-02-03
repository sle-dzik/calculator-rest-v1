package com.interview.app.calculator.operation;

class PlusOperation implements Operation {

    @Override
    public OperationPriority getPriority() {
        return OperationPriority.LOW;
    }

    @Override
    public Double calculate(Double a, Double b) {
        return a + b;
    }
}
