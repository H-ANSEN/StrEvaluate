package com.h_ansen.strevaluate;

import com.h_ansen.strevaluate.parser.Parser;
import com.h_ansen.strevaluate.parser.expression.Expression;

public class StrEvaluate {

    public StrEvaluate() {

    }

    public double eval(String expression) {
        Parser parser = new Parser(expression);
        Expression expr = parser.parse(0);
        return expr.evaluate();
    }
    
}
