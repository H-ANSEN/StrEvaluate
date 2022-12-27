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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PowerOperatorTest {
    
    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "42 ^ 3            : 74088.0",
            "1.5 ^ 3.5         : 4.133513941",
            "2 ^ 2 ^ 2         : 16.0",
            "1.1 ^ 0.11 ^ 12.2 : 1.0",
            "123456789 ^ 2     : 1.524157875019052E16"
        }
    )
    public void PositiveEvaluationTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.00001);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "-2 ^ 3         : -8.0",
            "-1.5 ^ 5       : -7.59375",
            "-5 ^ 2 ^ 3     :  390625.0",
            "-1.1 ^ 4 ^ 5   :  2.432817896953684E42",
            "-123456789 ^ 2 :  1.524157875019052E16"
        }
    )
    public void NegativeBaseTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.00001);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "2 ^ -2         : 0.25",
            "1.5 ^ -3       : 0.2962962963",
            "5 ^ -2 ^ -3    : 0.817765434",
            "1.1 ^ 3 ^ -4   : 1.001177361",
            "123456789 ^ -2 : 6.561000119410201E-17"
        }
    )
    public void NegativeExponentTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.00001);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "-2 ^ -2    :  0.25",
            "-12.5 ^ -5 : -3.2768E-6",
        }
    )
    public void NegativeBaseAndExponentTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.00001);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "(1 + 1) ^ 2       : 4.0",
            "(5 - 1) ^ 2       : 16.0",
            "2 ^ (1 + 1)       : 4.0",
            "2 ^ (5 - 1)       : 16.0",
            "(1 + 1) ^ (1 + 1) : 4.0"
        }
    )
    public void GroupingPowerTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.00001);
    }

}
