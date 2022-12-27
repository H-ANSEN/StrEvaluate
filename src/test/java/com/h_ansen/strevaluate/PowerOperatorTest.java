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
