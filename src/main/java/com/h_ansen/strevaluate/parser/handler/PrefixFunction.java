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

import com.h_ansen.strevaluate.Function;
import com.h_ansen.strevaluate.parser.Parser;
import com.h_ansen.strevaluate.parser.expression.Expression;
import com.h_ansen.strevaluate.parser.expression.FunctionExpression;
import com.h_ansen.strevaluate.tokenizer.Token;

public class PrefixFunction implements PrefixHandler {

    @Override
    public Expression parsePrefix(Parser parser, Token token) {
        
        if (!parser.functions().containsKey(token.getData())) {
            throw new IllegalArgumentException("Could not parse unknown function: \"" + token.getData() + "\"");
        }

        Function function = parser.functions().get(token.getData());
        double[] args = new double[function.arguments()];

        // Check for and consume opening left paren
        parser.consume(Token.LEFT_PAREN, 
            "Expected \"(\" after function name: \"" + function.name() + "\"");

        // Main parsing loop for functions. The left parentheses after a function name
        // has just been consumed, we now itterate till the right parentheses is found
        // along the way each expression delmited by `,` (comma) tokens is parsed and 
        // comma tokens are consumed
        int i = 0;
        while (parser.peekNextToken() != Token.RIGHT_PAREN) {

            if (parser.peekNextToken() == Token.COMMA) {
                parser.consume(Token.COMMA, null);
            }

            // To many function arguments passed to function
            if (i >= function.arguments()) {
                throw new IllegalArgumentException(
                    "Function \"" + function.name() + 
                    "\" expected " + function.arguments() +
                    " arguments but found at least " + (i + 1));
            }

            // Here each argument is parsed then evaluated and stored into the arguments array
            args[i] = parser.parse(0).evaluate();
            i++;
        }

        // Number of function arguments did not match expected number of arguments
        if (i != function.arguments()) {
            throw new IllegalArgumentException(
                    "Function \"" + function.name() + 
                    "\" expected " + function.arguments() +
                    " arguments but only found " + i);
        }

        // Check for and consune closing right paren
        parser.consume(Token.RIGHT_PAREN, 
            "Expected closing \")\" on function call \"" + function.name() + "\"");

        return new FunctionExpression(function, args);
    }

}
