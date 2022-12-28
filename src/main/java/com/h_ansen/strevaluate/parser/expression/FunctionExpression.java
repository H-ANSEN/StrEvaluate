package com.h_ansen.strevaluate.parser.expression;

import com.h_ansen.strevaluate.Function;

public class FunctionExpression extends Expression {

    private final Function function;
    private final double[] args;

    public FunctionExpression(Function function, double[] args) {
        this.function = function;
        this.args = args;
    }

    @Override
    public double evaluate() {
        return function.run(args);
    }
    
}
