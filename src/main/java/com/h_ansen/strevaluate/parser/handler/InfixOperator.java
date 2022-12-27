/**
 * MIT License
 *
 * Copyright (c) 2022 Teague Hansen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE. 
 */

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
