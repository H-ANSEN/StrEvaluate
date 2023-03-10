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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class FunctionsTest {

    @Test
    public void SimpleFunctionTest() {
        Function TEST_FUNC = new Function("test", 1) {
            public double run(double... args) { 
                return args[0] * 2; 
            }
        };

        StrEvaluate evaluator = new StrEvaluate();
        evaluator.addFunction(TEST_FUNC);

        assertEquals(4.0, evaluator.eval("test(2)"));
        assertEquals(6.0, evaluator.eval("test(3)"));
        assertEquals(8.0, evaluator.eval("test(4)"));
        assertEquals(10.0, evaluator.eval("test(5)"));

        evaluator.removeFunction("test");
    }

    @Test
    public void NestedFunctionTest() {
        Function TEST_FUNC = new Function("test", 1) {
            public double run(double... args) { 
                return args[0] * 2; 
            }
        };

        StrEvaluate evaluator = new StrEvaluate();
        evaluator.addFunction(TEST_FUNC);

        assertEquals(8.0, evaluator.eval("test(test(2))"));
        assertEquals(16.0, evaluator.eval("test(test(test(2)))"));
        assertEquals(32.0, evaluator.eval("test(test(test(test(2))))"));
        assertEquals(64.0, evaluator.eval("test(test(test(test(test(2)))))"));

        evaluator.removeFunction("test");
    }

    @Test
    public void ExpressionFunctionArgumentTest() {
        Function TEST_FUNC = new Function("test", 1) {
            public double run(double... args) { 
                return args[0] * 2; 
            }
        };

        StrEvaluate evaluator = new StrEvaluate();
        evaluator.addFunction(TEST_FUNC);

        assertEquals(8.0, evaluator.eval("test(2 + 2)"));
        assertEquals(0.0, evaluator.eval("test(2 - 2)"));
        assertEquals(16.0, evaluator.eval("test(4 * 2)"));
        assertEquals(4.0, evaluator.eval("test(4 / 2)"));
        assertEquals(32.0, evaluator.eval("test((2 + 2) ^ 2)"));
        assertEquals(150.0, evaluator.eval("test(5 * (10 + 5))"));

        evaluator.removeFunction("test");
    }

    @Test
    public void MultipleFunctionArgumentTest() {
        Function TEST_FUNC = new Function("sum", 3) {
            public double run(double... args) { 
                return args[0] + args[1] + args[2]; 
            }
        };

        StrEvaluate evaluator = new StrEvaluate();
        //evaluator.addFunction(TEST_FUNC);

        assertEquals(6.0, evaluator.eval("sum(1, 2, 3)"));
        assertEquals(8.0, evaluator.eval("sum(1 + 2, 2, 3)"));
        assertEquals(10.0, evaluator.eval("sum(1 + 2, 2 + 2, 3)"));
        assertEquals(12.0, evaluator.eval("sum(1 + 2, 2 + 2, 3 + 2)"));

        evaluator.removeFunction("sum");
    }

    @Test(expected = IllegalArgumentException.class)
    public void DuplicateFunctionTest() {
        Function TEST_FUNC = new Function("sum", 3) {
            public double run(double... args) { 
                return args[0] + args[1] + args[2]; 
            }
        };
        StrEvaluate evaluator = new StrEvaluate();
        evaluator.addFunction(TEST_FUNC);
        evaluator.addFunction(TEST_FUNC);
        evaluator.removeFunction("sum");
    }


    @Test(expected = RuntimeException.class)
    public void MissingOpeningParenthesesTest() {
        Function TEST_FUNC = new Function("sum", 3) {
            public double run(double... args) { 
                return args[0] + args[1] + args[2]; 
            }
        };
        StrEvaluate evaluator = new StrEvaluate();
        evaluator.addFunction(TEST_FUNC);

        evaluator.eval("sum 1, 2, 3)");
        evaluator.removeFunction("sum");
    }

    @Test(expected = RuntimeException.class)
    public void MissingClosingParenthesesTest() {
        Function TEST_FUNC = new Function("sum", 3) {
            public double run(double... args) { 
                return args[0] + args[1] + args[2]; 
            }
        };
        StrEvaluate evaluator = new StrEvaluate();
        evaluator.addFunction(TEST_FUNC);

        evaluator.eval("sum(1, 2, 3");
        evaluator.removeFunction("sum");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ToManyArgumentsTest() {
        Function TEST_FUNC = new Function("sum", 3) {
            public double run(double... args) { 
                return args[0] + args[1] + args[2]; 
            }
        };
        StrEvaluate evaluator = new StrEvaluate();
        evaluator.addFunction(TEST_FUNC);

        evaluator.eval("sum(1, 2, 3, 4)");
        evaluator.removeFunction("sum");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ToFewArgumentsTest() {
        Function TEST_FUNC = new Function("sum", 3) {
            public double run(double... args) { 
                return args[0] + args[1] + args[2]; 
            }
        };
        StrEvaluate evaluator = new StrEvaluate();
        evaluator.addFunction(TEST_FUNC);

        evaluator.eval("sum(1, 2)");
        evaluator.removeFunction("sum");
    }

}
