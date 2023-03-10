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

public class MultiplicationOperatorTest {
    
    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "10 * 3                :  30.0",
            "5.3 * 0.1             :  0.53",
            "1 * 2 * 3             :  6.0",
            "9.001 * 0.11 * 12.2   :  12.079342",
            "123456789 * 123456789 :  1.524157875019052E16"
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
            "-12 * -4                :  48.0",
            "-1.01 * -13.2           :  13.332",
            "-1 * -9 * -2            : -18.0",
            "-0.1 * -5.55 * -13.4    : -7.437",
            "-987654321 * -987654321 :  9.7546105778997107E17"
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
            "7 * -1                  : -7.0",
            "-18 * 3                 : -54.0",
            "12.9 * -4.31            : -55.599",
            "-6.32 * 7.1             : -44.872",
            "5 * -12 * -8            :  480.0",
            "-5.1 * 2.4 * -3.31      :  40.5144",
            "-342943053 * 987654321  : -3.3870918815238202E17"
        }
    )
    public void MixedSignEvaluationTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void BigMultipicationTest() {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval("200000000000000000.0 * 700000000000100000.0");
        assertEquals(1.4000000000002E35, result);

        result = evaluator.eval("100000000000000000.0 * 0.1");
        assertEquals(1.0E16, result);

        result = evaluator.eval("100000000000000000.0 * 0");
        assertEquals(0.0, result);
    }

    @Test
    public void SmallMultiplicationTest() {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval("0.0000000000000001 * 0.1");
        assertEquals(1.0E-17, result);

        result = evaluator.eval("0 * 0.1");
        assertEquals(0.0, result);

        result = evaluator.eval("0.0001 * 0.0000000001");
        assertEquals(1.0000000000000002E-14, result, 0.000001);
    }

}
