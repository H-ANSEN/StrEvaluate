package com.h_ansen.strevaluate.parser.handler;

import com.h_ansen.strevaluate.parser.Parser;
import com.h_ansen.strevaluate.parser.expression.Expression;
import com.h_ansen.strevaluate.parser.expression.NumberExpression;
import com.h_ansen.strevaluate.tokenizer.Token;

public class PrefixNumber implements PrefixHandler {

    @Override
    public Expression parsePrefix(Parser parser, Token token) {
        // Numbers are terminal so no further parsing is required, the number
        // is returned as a expression
        return new NumberExpression(token.getData());
    }
    
}
