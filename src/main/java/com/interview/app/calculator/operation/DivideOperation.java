package com.interview.app.calculator.operation;

class DivideOperation implements Operation {

    @Override
    public OperationPriority getPriority() {
        return OperationPriority.HIGH;
    }

    @Override
    public Double calculate(Double a, Double b) {
        if (b == 0) {
            throw new ArithmeticException("Divide by 0 is prohibited");
        }
        return a / b;
    }
}
