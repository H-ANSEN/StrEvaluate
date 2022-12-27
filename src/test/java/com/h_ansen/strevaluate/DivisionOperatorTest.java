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

public class DivisionOperatorTest {

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "5 / 5                 : 1.0",
            "1 / 6.3               : 0.15873015873015872",
            "1 / 2 / 3             : 0.16666666666666666",
            "5.1 / 2.2 / 10.6      : 0.21869639794168094",
            "123456789 / 12345678  : 10.00000072900006"
        }
    )
    public void PositiveEvaluationTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "-7 / -4                 :  1.75",
            "-8.44 / -9.1            :  0.9274725274725275",
            "-7 / -5 / -3            : -0.4666666666666666",
            "-7.5 / -6.33 / -1.11    : -1.0674181290295033",
            "-987654321 / -98765432  :  10.000000010125"
        }
    )
    public void NegitiveEvaluationTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "7 / -3                  : -2.3333333333333335",
            "-3 / 7                  : -0.42857142857142855",
            "8.7 / -2.3              : -3.782608695652174",
            "-1.7 / 6.12             : -0.2777777777777778",
            "12 / 4 / -2             : -1.5",
            "-6.3 / 155.2 / -23.8    :  0.0017055791388720437",
            "-34294305 / 987654321   : -0.03472298381206596"
        }
    )
    public void MixedSignEvaluationTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.000000000000001);
    }

    @Test
    public void BigDivisionTest() {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval("100000000000000000.0 / 100000000000000000.0");
        assertEquals(1.0, result);

        result = evaluator.eval("100000000000000000.0 / 0.1");
        assertEquals(1.0E18, result);

        result = evaluator.eval("0.13 / 100000000000000000.0");
        assertEquals(1.3E-18, result);
    }

    @Test
    public void SmallDivisionTest() {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval("0.1 / 0.2");
        assertEquals(0.5, result);

        result = evaluator.eval("0.0000000000000001 / 0.1");
        assertEquals(9.999999999999999E-16, result);

        result = evaluator.eval("0 / 0.1");
        assertEquals(0.0, result);
    }

}
