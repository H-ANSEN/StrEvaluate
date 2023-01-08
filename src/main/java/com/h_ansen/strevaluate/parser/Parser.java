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

package com.h_ansen.strevaluate.parser;

import java.util.HashMap;
import java.util.Map;

import com.h_ansen.strevaluate.Function;
import com.h_ansen.strevaluate.parser.expression.Expression;
import com.h_ansen.strevaluate.parser.handler.InfixHandler;
import com.h_ansen.strevaluate.parser.handler.PrefixFunction;
import com.h_ansen.strevaluate.parser.handler.InfixOperator;
import com.h_ansen.strevaluate.parser.handler.PrefixGrouping;
import com.h_ansen.strevaluate.parser.handler.PrefixHandler;
import com.h_ansen.strevaluate.parser.handler.PrefixNumber;
import com.h_ansen.strevaluate.parser.handler.PrefixOperator;
import com.h_ansen.strevaluate.tokenizer.Token;
import com.h_ansen.strevaluate.tokenizer.Tokenizer;

public class Parser extends Tokenizer {

    private static final Map<Token, InfixHandler> INFIX_HANDLER_MAP = new HashMap<>();
    private static final Map<Token, PrefixHandler> PREFIX_HANDLER_MAP = new HashMap<>();
    private static final Map<String, Function> FUNCTIONS = new HashMap<>();

    static {
        PREFIX_HANDLER_MAP.put(Token.PLUS, new PrefixOperator(6));
        PREFIX_HANDLER_MAP.put(Token.MINUS, new PrefixOperator(6));
        PREFIX_HANDLER_MAP.put(Token.LEFT_PAREN, new PrefixGrouping());
        PREFIX_HANDLER_MAP.put(Token.NUMBER, new PrefixNumber());
        PREFIX_HANDLER_MAP.put(Token.NAME, new PrefixFunction());

        INFIX_HANDLER_MAP.put(Token.EQUALS_EQUALS, new InfixOperator(1, false));
        INFIX_HANDLER_MAP.put(Token.N_EQUALS, new InfixOperator(1, false));
        INFIX_HANDLER_MAP.put(Token.PLUS, new InfixOperator(3, false));
        INFIX_HANDLER_MAP.put(Token.MINUS, new InfixOperator(3, false));
        INFIX_HANDLER_MAP.put(Token.MULTIPLY, new InfixOperator(4, false));
        INFIX_HANDLER_MAP.put(Token.DIVIDE, new InfixOperator(4, false));
        INFIX_HANDLER_MAP.put(Token.POWER, new InfixOperator(5, true));
    }

    public Expression parse(int precedence) {
        Token token = nextToken();

        PrefixHandler prefixHandler = PREFIX_HANDLER_MAP.get(token);
        if (prefixHandler == null)
            throw new RuntimeException(
                "Expression begins with invalid token: \'" + token.getData() + "\'");

        Expression left = prefixHandler.parsePrefix(this, token);

        // While we do not incounter another prefix operator
        while (precedence < getPrecedence()) {
            token = nextToken();

            InfixHandler infixHandler = INFIX_HANDLER_MAP.get(token);
            left = infixHandler.parseInfix(this, left, token);
        }

        return left;
    }

    public void addFunction(Function function) {
        if (FUNCTIONS.containsKey(function.name())) {
            throw new IllegalArgumentException("Duplicate function name: \"" + function.name() + "\"");
        }
        FUNCTIONS.put(function.name(), function);
    }

    public void removeFunction(String functionName) {
        FUNCTIONS.remove(functionName);
    }

    /**
     * Returns a map of functions that have been registered to this parser 
     * where the key is a functions name and the value is the function object
     * 
     * @return a map of functions registed to the parser
     */
    public Map<String, Function> functions() {
        return FUNCTIONS;
    }

    /**
     * Checks to see if the specified token is the next token in the input
     * string. If expected matches the next token the token is consumed 
     * otherwise a RunTimeException is thorwn with the specified message.
     * 
     * @param expected the token expected to be next
     * @param message an error message to display if the next token does not 
     * match expected
     */
    public void consume(Token expected, String message) {
        Token t = nextToken();
        if (t != expected)
            throw new RuntimeException(message);   
    }

    /**
     * @return Returns the precedence of the next token if it is a infix token. 
     * Otherwise returns a precedence of 0
     */
    private int getPrecedence() {
        InfixHandler infixHandler = INFIX_HANDLER_MAP.get(peekNextToken());
        if (infixHandler != null) return infixHandler.getPrecedence();
        return 0;
    }

}
