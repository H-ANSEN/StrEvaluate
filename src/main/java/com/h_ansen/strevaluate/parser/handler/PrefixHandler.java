package com.h_ansen.strevaluate.parser.handler;

import com.h_ansen.strevaluate.parser.Parser;
import com.h_ansen.strevaluate.parser.expression.Expression;
import com.h_ansen.strevaluate.tokenizer.Token;

public interface PrefixHandler {
    
    Expression parsePrefix(Parser parser, Token token);

}
