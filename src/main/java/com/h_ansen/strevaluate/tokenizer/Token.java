package com.h_ansen.strevaluate.tokenizer;

public enum Token {
    
    PLUS        ("+"),
    MINUS       ("-"),
    MULTIPLY    ("*"),
    DIVIDE      ("/"),
    POWER       ("^"),
    LEFT_PAREN  ("("),
    RIGHT_PAREN (")"),
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
