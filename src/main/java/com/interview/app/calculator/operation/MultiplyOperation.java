package com.interview.app.calculator.operation;

class MultiplyOperation implements Operation {

    @Override
    public OperationPriority getPriority() {
        return OperationPriority.HIGH;
    }

    @Override
    public Double calculate(Double a, Double b) {
        return a * b;
    }
}
