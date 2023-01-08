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
