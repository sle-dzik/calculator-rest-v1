package com.interview.app.calculator;

import com.interview.app.calculator.operation.Operation;
import com.interview.app.calculator.operation.OperationProvider;
import com.interview.app.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
class Calculator {

    private final ExpressionSplitter expressionSplitter;
    private final OperationProvider operationProvider;

    public Calculator(ExpressionSplitter expressionSplitter, OperationProvider operationProvider) {
        this.expressionSplitter = expressionSplitter;
        this.operationProvider = operationProvider;
    }

    public Double calculate(String input) {
        List<String> splittedExpression = expressionSplitter.split(input);

        LinkedList<Character> operatorStack = new LinkedList<>();
        LinkedList<Double> valueStack = new LinkedList<>();

        for (String expressionPart : splittedExpression) {
            log.debug("Expression element:[{}], operatorStack:{}, valueStack:{}", expressionPart, operatorStack, valueStack);
            if (StringUtils.isNumber(expressionPart)) {
                valueStack.push(Double.valueOf(expressionPart));
            } else if (operationProvider.isSupportedOpt(expressionPart.charAt(0))) {
                char operatorSymbol = expressionPart.charAt(0);
                if (operatorStack.isEmpty() || !operationProvider.isSupportedOpt(operatorStack.peek()) || isHigherPrecedence(operatorSymbol, operatorStack.peek())) {
                    operatorStack.push(operatorSymbol);
                } else {
                    while (!operatorStack.isEmpty() && operationProvider.isSupportedOpt(operatorStack.peek()) && isLessOrEqualPrecedence(operatorSymbol, operatorStack.peek())) {
                        if (valueStack.size() >= 2) {
                            valueStack.push(calculateValue(operatorStack.pop(), valueStack));
                        }
                    }
                    operatorStack.push(operatorSymbol);
                }

            } else if (expressionPart.charAt(0) == '(') {
                operatorStack.push(expressionPart.charAt(0));
            } else if (expressionPart.charAt(0) == ')') {
                Character startingParenthesis = null;
                while (!operatorStack.isEmpty()) {
                    if (valueStack.size() >= 2 && operationProvider.isSupportedOpt(operatorStack.peek())) {
                        valueStack.push(calculateValue(operatorStack.pop(), valueStack));

                    } else if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                        startingParenthesis = operatorStack.pop();
                        break;
                    }
                }
                if (startingParenthesis == null) {
                    throw new IllegalArgumentException("Missing parenthesis");
                }
            } else {
                throw new IllegalArgumentException("Not supported expression element: " + expressionPart.charAt(0));
            }
        }

        while (!operatorStack.isEmpty()) {
            char operator = operatorStack.pop();
            if (valueStack.size() >= 2) {
                valueStack.push(calculateValue(operator, valueStack));
            } else if (operator == '(') {
                throw new IllegalArgumentException("Missing parenthesis");
            } else {
                throw new IllegalArgumentException("Invalid expression");
            }

        }

        return valueStack.pop();
    }

    private Double calculateValue(Character operator, LinkedList<Double> valueStack) {
        Double secondValue = valueStack.pop();
        Double firstValue = valueStack.pop();
        log.debug("Calculate {} {} {}", firstValue, operator, secondValue);
        return operationProvider.getOperation(operator).calculate(firstValue, secondValue);
    }

    private boolean isHigherPrecedence(Character firstOperator, Character secondOperator) {
        Operation operation1 = operationProvider.getOperation(firstOperator);
        Operation operation2 = operationProvider.getOperation(secondOperator);
        return operation1.getPriority().getValue() > operation2.getPriority().getValue();
    }

    private boolean isLessOrEqualPrecedence(char firstOperator, char secondOperator) {
        Operation operation1 = operationProvider.getOperation(firstOperator);
        Operation operation2 = operationProvider.getOperation(secondOperator);
        return operation1.getPriority().getValue() <= operation2.getPriority().getValue();
    }
}
