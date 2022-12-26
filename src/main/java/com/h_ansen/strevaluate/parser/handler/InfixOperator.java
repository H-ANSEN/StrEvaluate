package com.h_ansen.strevaluate.parser.handler;

import com.h_ansen.strevaluate.parser.Parser;
import com.h_ansen.strevaluate.parser.expression.Expression;
import com.h_ansen.strevaluate.parser.expression.OperatorExpression;
import com.h_ansen.strevaluate.tokenizer.Token;

public class InfixOperator implements InfixHandler {

    private final int precedence;
    private final boolean rightAssocitve;

    public InfixOperator(int precedence, boolean rightAssocitve) {
        this.precedence = precedence;
        this.rightAssocitve = rightAssocitve;
    }

    @Override
    public int getPrecedence() {
        return this.precedence;
    }

    @Override
    public Expression parseInfix(Parser parser, Expression left, Token token) {
        // If we are in a `parseInfix` call we have just parsed an expression 
        // to the left and have then hit an operator. We now parse the 
        // expression to the right and create a operator expresion of the form
        // {expression} + {operator} + {expression}

        Expression right = parser.parse(precedence - (rightAssocitve ? 1 : 0));

        return new OperatorExpression(left, token, right);
    }
    
}
