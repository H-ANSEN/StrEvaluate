package com.h_ansen.strevaluate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InequalityOperatorTest {
    
    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "1 != 3             : 1.0",
            "1 != 1             : 0.0",
            "1.343 != 2.132     : 1.0",
            "1.110 != 1.110     : 0.0",
            "123456 != 123457 : 1.0"
        }
    )
    public void SimpleInequalityTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "1 + 1 != 1 + 1.1      : 1.0",
            "1 + 4 != 3 + 2        : 0.0",
            "(10 / 2) ^ 3 != 17.0  : 1.0",
            "4.2 != 12.61 / 3      : 1.0",
            "9.4 * 5 != 47         : 0.0"
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
            "1 + 1 != 1 + 1.1      : true",
            "1 + 4 != 3 + 2        : false",
            "(10 / 2) ^ 3 != 17.0  : true",
            "4.2 != 12.61 / 3      : true",
            "9.4 * 5 != 47         : false"
        }
    )
    public void EqualityAsBoolTest(String expression, Boolean expected) {
        StrEvaluate evaluator = new StrEvaluate();
        boolean result = evaluator.evalAsBool(expression);
        assertEquals(expected, result);
    }

}
