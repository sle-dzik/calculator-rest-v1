package com.interview.app.calculator.operation;

public interface Operation {

    OperationPriority getPriority();

    Double calculate(Double a,Double b);
}
