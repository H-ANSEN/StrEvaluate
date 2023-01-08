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

public class EqualityOperatorTest {

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "1 == 1             : 1.0",
            "1 == 2             : 0.0",
            "1.001 == 1.001     : 1.0",
            "1.110 == 1.111     : 0.0",
            "9999999 == 9999999 : 1.0"
        }
    )
    public void SimpleEqualityTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "1 + 1 == 1 + 1        : 1.0",
            "1 + 1 == 1 + 2        : 0.0",
            "(10 / 2) ^ 3 == 125.0 : 1.0",
            "4.2 == 12.6 / 3       : 1.0",
            "9.4 * 5 == 48         : 0.0"
        }
    )
    public void ExpressionEqualityTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "1 + 1 == 1 + 1        : true",
            "1 + 1 == 1 + 2        : false",
            "(10 / 2) ^ 3 == 125.0 : true",
            "4.2 == 12.6 / 3       : true",
            "9.4 * 5 == 48         : false"
        }
    )
    public void EqualityAsBoolTest(String expression, Boolean expected) {
        StrEvaluate evaluator = new StrEvaluate();
        boolean result = evaluator.evalAsBool(expression);
        assertEquals(expected, result);
    }

}
