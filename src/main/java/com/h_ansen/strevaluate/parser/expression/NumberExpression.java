package com.h_ansen.strevaluate.parser.expression;

public class NumberExpression extends Expression {

    private final String number;

    public NumberExpression(String number) {
        this.number = number;
    }

    @Override
    public double evaluate() {
        return Double.valueOf(number);
    }
    
}
