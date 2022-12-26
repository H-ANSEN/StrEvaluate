package com.h_ansen.strevaluate.parser.expression;

import com.h_ansen.strevaluate.tokenizer.Token;

public class OperatorExpression extends Expression {

    private final Expression left;
    private final Token operator;
    private final Expression right;

    public OperatorExpression(Expression left, Token operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public double evaluate() {
        double numberL = left.evaluate();
        double numberR = right.evaluate(); 

        switch (operator) {
            case PLUS:     return numberL + numberR;
            case MINUS:    return numberL - numberR;
            case MULTIPLY: return numberL * numberR;
            case DIVIDE:   return numberL / numberR;
            case POWER:    return Math.pow(numberL, numberR);
            default:
                throw new UnsupportedOperationException(
                    "Infix expression using operator " + operator + " not supported.");
        }

    }
    
}
