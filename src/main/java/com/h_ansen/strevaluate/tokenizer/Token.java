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

public enum Token {
    
    PLUS        ("+"),
    MINUS       ("-"),
    MULTIPLY    ("*"),
    DIVIDE      ("/"),
    POWER       ("^"),
    LEFT_PAREN  ("("),
    RIGHT_PAREN (")"),
    COMMA       (","),
    NAME        (""),
    NUMBER      (""),
    END         ("");

    private String data;

    private Token(String data) {
        this.data = data;
    }

    /**
     * Returns the data associated with this token. Tokens by default have data
     * corresponding to their character value. During the tokenization phase of 
     * evaluation number tokens are given a string value representation of 
     * the number they represent and function tokens are assigned the string 
     * value of there name.
     * 
     * @return data associated with this token
     */
    public String getData() {
        return this.data;
    }

    /**
     * Sets the data associated with this token and returns this token.
     * 
     * @param data data to be associated with this token
     * @return the token that data is being associated with
     */
    protected Token setData(String data) {
        this.data = data;
        return this;
    }
    
}
