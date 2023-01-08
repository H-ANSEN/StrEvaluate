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

    private int ip; // Instruction pointer, points to index of the next token1 in input string
    private String input;

    public void setInput(String input) {
        this.input = input;
        this.ip = 0;
    }

    public Token nextToken() {

        // Skip whitespace characters
        while (!isAtEnd() && isWhitespace(peek())) ip++;

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
            case ',': return Token.COMMA;
            case '=': if (expect('=')) return Token.EQUALS_EQUALS;
                else throw new RuntimeException("Expected `=` after equals: (==)");
            case '!': if (expect('=')) return Token.N_EQUALS;
                else throw new RuntimeException("Expected `=` after bang: (!=)");
            default:
                if (Character.isDigit(c)) return number();
                if (Character.isLetter(c)) return name();
                throw new RuntimeException("Unexpected character: `" + c + "`");
        }

    }

    public Token peekNextToken() {
        int tmp = ip;
        Token t = nextToken();
        ip = tmp;
        return t;        
    }

    private char peek() {
        return input.charAt(ip);
    }

    private boolean expect(char c) {
        if (peek() == c) {
            ip++; // consume expected
            return true;
        }
        return false;
    }

    private Token number() {
        int start = ip - 1;
        while (!isAtEnd() && (Character.isDigit(peek()) || peek() == '.')) ip++;
        return Token.NUMBER.setData(input.substring(start, ip));
    }

    private Token name() {
        int start = ip - 1;
        while (!isAtEnd() && Character.isAlphabetic(peek())) ip++;
        return Token.NAME.setData(input.substring(start, ip));
    }

    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || 
               c == '\r' || c == '\n';
    }

    private boolean isAtEnd() {
        return ip >= input.length();
    }
    
}
