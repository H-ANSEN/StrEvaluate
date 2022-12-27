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

package com.h_ansen.strevaluate.tokenizer;

public class Tokenizer {

    public Tokenizer(String input) {
        this.input = input;
    }

    public Token nextToken() {

        // Skip whitespace characters
        while (!isAtEnd() && isWhitespace()) ip++;

        // End of expression reached return special end token1
        if (isAtEnd()) return Token.END;

        char c = input.charAt(ip++);

        switch (c) {
            case '(': return Token.LEFT_PAREN;  
            case ')': return Token.RIGHT_PAREN; 
            case '+': return Token.PLUS;
            case '*': return Token.MULTIPLY;
            case '/': return Token.DIVIDE;
            case '^': return Token.POWER;
            case '-': return Token.MINUS;
            default:
                if (Character.isDigit(c)) return number();
                //if (Character.isLetter(c)) return function();
                throw new RuntimeException("Unexpected character: `" + c + "`");
        }

    }

    public Token peekNextToken() {
        int tmp = ip;
        Token t = nextToken();
        ip = tmp;
        return t;        
    }

    private Token number() {
        int start = ip - 1;
        while (!isAtEnd() && (Character.isDigit(peek()) || peek() == '.')) ip++;
        return Token.NUMBER.setData(input.substring(start, ip));
    }

    // private Token function() {
    //     int start = ip - 1;
    //     while (!isAtEnd() && Character.isAlphabetic(peek())) ip++;
    //     return Token.FUNCTION.setData(input.substring(start, ip));
    // }

    private char peek() {
        return input.charAt(ip);
    }

    private boolean isWhitespace() {
        return Character.isWhitespace(input.charAt(ip));
    }

    private boolean isAtEnd() {
        return ip >= input.length();
    }

    private int ip; // Instruction pointer, points to index of the next token1 in input string
    private final String input;
    
}
