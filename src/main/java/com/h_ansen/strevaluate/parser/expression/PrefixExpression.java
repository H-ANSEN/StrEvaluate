package com.h_ansen.strevaluate.parser.expression;

import com.h_ansen.strevaluate.tokenizer.Token;

public class PrefixExpression extends Expression {

    private final Token token;
    private final Expression right;

    public PrefixExpression(Token token, Expression right) {
        this.token = token;
        this.right = right;
    }

    @Override
    public double evaluate() {
        switch (token) {
            case MINUS: return -right.evaluate();
            case PLUS:
            case NUMBER:
            case LEFT_PAREN: return right.evaluate();                
            default:
                throw new UnsupportedOperationException(
                    "Prefix expression using operator " + token + " not supported.");
        }

    }
    
}
