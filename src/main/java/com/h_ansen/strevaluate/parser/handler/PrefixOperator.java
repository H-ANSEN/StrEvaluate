package com.h_ansen.strevaluate.parser.handler;

import com.h_ansen.strevaluate.parser.Parser;
import com.h_ansen.strevaluate.parser.expression.Expression;
import com.h_ansen.strevaluate.parser.expression.PrefixExpression;
import com.h_ansen.strevaluate.tokenizer.Token;

public class PrefixOperator implements PrefixHandler {

    private final int precedence;

    public PrefixOperator(int precedence) {
        this.precedence = precedence;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    @Override
    public Expression parsePrefix(Parser parser, Token token) {
        // Prefix operators are followed by an expression to the right, the
        // most common case of this is negitive numbers, we must now parse the
        // expression to the right of the prefix operator

        Expression right = parser.parse(precedence);

        return new PrefixExpression(token, right);
    }
    
}
