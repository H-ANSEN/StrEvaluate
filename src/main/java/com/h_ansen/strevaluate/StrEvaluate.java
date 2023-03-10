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

package com.h_ansen.strevaluate;

import com.h_ansen.strevaluate.parser.Parser;
import com.h_ansen.strevaluate.parser.expression.Expression;

public class StrEvaluate {

    private final Parser expressionParser;
    
    public StrEvaluate() {
        expressionParser = new Parser();
    }

    /**
     * Adds a new function to this instance of StrEvaluate. If this function
     * appears in an expression it's run() method will be 
     * called using the arguments provided in the expression.
     * 
     * @param function the function to add to this instance
     * @throws IllegalArgumentException if this instance of StrEvaluate already
     * contains a function with the same name
     */
    public void addFunction(Function function) {
        expressionParser.addFunction(function);        
    }

    /**
     * Removes the function with the specified name from this instance of 
     * StrEvaluate.
     * 
     * @param functionName the name of the function to remove
     */
    public void removeFunction(String functionName) {
        expressionParser.removeFunction(functionName);
    }

    /**
     * Evaluates a mathimatical expression in string form and returns the 
     * result of the expression as a double value
     * 
     * @param expression an expression to be evaluated
     * @return the result of the specified expression
     */
    public double eval(String expression) {
        expressionParser.setInput(expression);
        Expression expr = expressionParser.parse(0);
        return expr.evaluate();
    }

    /**
     * Evaluates an array of mathimatical expressions in string form and 
     * returns an array of double values corrisponding to expressions evaluated
     * 
     * @param expressions an array of expressions to be evaluated
     * @return an array containing the evaluated result of each expression
     */
    public double[] eval(String[] expressions) {
        double[] results = new double[expressions.length];
        for (int i = 0; i < expressions.length; i++) {
            results[i] = eval(expressions[i]);
        }
        return results;
    } 
    
    /**
     * Evaluates a mathimatical comparison expression and returns a boolean
     * value based on the result of the expression. For example {@code 1 > 2}
     * would return a value; {@code false} and {@code 3 == 3} would return
     * {@code true}.
     *  
     * @param expression an expression to evaluate
     * @return a boolean value based on the result of evaluating the expression
     */
    public boolean evalAsBool(String expression) {
        double result = eval(expression);
        if (result == 1.0) return true;
        return false;
    }

}
