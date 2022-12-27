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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SubtractionOperatorTest {
    
    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "7 - 8                 : -1.0",
            "5.3 - 3.5             :  1.799999",
            "1 - 2 - 3             : -4.0",
            "8.3 - 1.1 - 1.2       :  6.0",
            "123456789 - 123456789 :  0.0"
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
            "-8 - -3                 : -5.0",
            "-1.5 - -9.1             :  7.6",
            "-3 - -1 - -8            :  6.0",
            "-43.2 - -5.33 - -12.22  : -25.65",
            "-987654321 - -987654321 :  0.0"
        }
    )
    public void NegitiveEvaluationTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.00001);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "7 - -34                 :  41.0",
            "-6 - 16                 : -22.0",
            "4.12 - -8.99            :  13.11",
            "-64.2 - 3.8             : -68.0",
            "6 - 12 - -8             :  2.0",
            "-6.3 - 77.1 - -12.3     : -71.1",
            "-342943053 - 987654321  : -1330597374"
        }
    )
    public void MixedSignEvaluationTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.000000000000001);
    }

    @Test
    public void SmallSubtractionTest() {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval("0.0000000000000001 - 0.1");
        assertEquals(-0.09999999999999991, result);

        result = evaluator.eval("0 - 0.1");
        assertEquals(-0.1, result);

        result = evaluator.eval("0.0001 - 0.0000000001");
        assertEquals(9.999990000000001E-5, result, 0.000001);
    }

}
