package com.h_ansen.strevaluate.parser.handler;

import com.h_ansen.strevaluate.parser.Parser;
import com.h_ansen.strevaluate.parser.expression.Expression;
import com.h_ansen.strevaluate.tokenizer.Token;

public class PrefixGrouping implements PrefixHandler {

    @Override
    public Expression parsePrefix(Parser parser, Token token) {
        // If we are in a 'parsePrefix' call of 'PrefixGrouping' the previous
        // token process was a left parentheses, we now parse till a right 
        // paren is incountered (or not)

        Expression expression = parser.parse(0);
        parser.consume(Token.RIGHT_PAREN, "Expected closing parentheses: \')\'");

        return expression;
    }
    
}
