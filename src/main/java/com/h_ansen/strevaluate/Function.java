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

/**
 * The class representing a callable function that takes in a variable number
 * of arguments and returns a result of type double.
 * <br><br>
 * Each function must be given a alphabetical name and variable number of
 * arguments it operates on. Once a function and its {@link #run} method have
 * been implemented and registered to a {@link StrEvaluate} object the function
 * can be called within a string expression given it has the following format:
 * <br><br>
 * {@code function_name(args)}
 */
public abstract class Function {

    private final String name;
    private final int numArgs;

    /**
     * Constructs a new function capible of taking in a variable number of
     * arguments and producing a double result. Each function requires a name,
     * the name must be alphabetic and cannot contain any numbers. A function
     * also takes in a variable number of arguments that can range from 1 to
     * Integer.MAX_VALUE.
     * 
     * @param name the name of this function
     * @param numArgs the number of arguments this function takes
     */
    public Function(String name, int numArgs) {
        this.name = name;
        this.numArgs = numArgs;
    }

    /**
     * Returns the number of arguments that this function takes.
     * 
     * @return number of arguments for this function
     */
    public int arguments() {
        return this.numArgs;
    }

    /**
     * Returns the name of this function.
     * 
     * @return the name of this function
     */
    public String name() {
        return this.name;
    }

    /**
     * The function body that is called when this function appears in an 
     * expression. When implementing a function the function body will be 
     * provided with the number of arguments specified in this functions
     * constructor, with the first argument being {@code args[0]} and the last
     * being {@code args[this.arguments() - 1]}.
     * 
     * @param args arguments of this function
     * @return the result of this function
     */
    public abstract double run(double... args);
    
}
