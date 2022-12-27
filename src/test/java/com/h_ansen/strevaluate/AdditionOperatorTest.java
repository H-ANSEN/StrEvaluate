package com.h_ansen.strevaluate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AdditionOperatorTest {
    
    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "1 + 1                 : 2.0",
            "1.2 + 3.5             : 4.7",
            "1 + 2 + 3             : 6.0",
            "1.2 + 6.3 + 1.6       : 9.1",
            "123456789 + 123456789 : 246913578"
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
            "-4 + -2                 : -6.0",
            "-5.2 + -1.3             : -6.5",
            "-7 + -1 + -9            : -17.0",
            "-21.3 + -2.5 + -1.23    : -25.03",
            "-987654321 + -987654321 : -1975308642"
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
            "4 + -10                 : -6.0",
            "-4 + 10                 :  6.0",
            "9.23 + -1.92            :  7.31",
            "-5.1 + 12.8             :  7.7",
            "17 + 1 + -9             :  9.0",
            "-3.3 + 2.3 + -11.8      : -12.8",
            "-342943053 + 987654321  :  644711268"
        }
    )
    public void MixedSignEvaluationTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.000000000000001);
    }

    @Test
    public void BigAdditionTest() {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval("100000000000000000.0 + 100000000000000000.0");
        assertEquals(200000000000000000.0, result);

        result = evaluator.eval("100000000000000000.0 + 0.1");
        assertEquals(100000000000000000.1, result);

        result = evaluator.eval("100000000000000000.0 + 0");
        assertEquals(100000000000000000.0, result);
    }

    @Test
    public void SmallAdditionTest() {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval("0.1 + 0.1");
        assertEquals(0.2, result);

        result = evaluator.eval("0.0000000000000001 + 0.1");
        assertEquals(0.1000000000000001, result);

        result = evaluator.eval("0 + 0.1");
        assertEquals(0.1, result);
    }

}