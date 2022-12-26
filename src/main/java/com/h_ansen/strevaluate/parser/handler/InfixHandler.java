package com.h_ansen.strevaluate.parser.handler;

import com.h_ansen.strevaluate.parser.Parser;
import com.h_ansen.strevaluate.parser.expression.Expression;
import com.h_ansen.strevaluate.tokenizer.Token;

/**
 * Classes implementing the InfixHandler class handle the parsing of infix
 * expressions. A infix is represented by the following format:
 * <br><br>
 * {@code expression} + {@code operator} + {@code expression}
 * <br><br>
 */
public interface InfixHandler {

    /**
     * Returns the precedence of the operator associated with this infix
     * expression.
     * 
     * @return the opertator precedence of this expression
     */
    int getPrecedence();

    /**
     * 
     * 
     * @param parser the parser
     * @param left the expression the directly proceeded the infix operator
     * @param token the infix operator associated with this expression
     * @return 
     */
    Expression parseInfix(Parser parser, Expression left, Token token);
    
}
