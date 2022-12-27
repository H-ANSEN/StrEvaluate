package com.h_ansen.strevaluate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PrefixMinusOperator {
    
    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "-(1)       : -1.0",
            "-(-1)      :  1.0",
            "-(5 + 10)  : -15.0",
            "-(15 - 50) :  35.0",
            "--(1)      :  1.0",
            "--(-1)     : -1.0"
        }
    )
    public void NegitiveGroupingTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.00001);
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ':',
        value = {
            "-1     : -1.0",
            "--1    :  1.0",
            "---1   : -1.0",
            "----1  :  1.0",
            "-----1 : -1.0"
        }
    )
    public void SimpleNegitiveTest(String expression, Double expected) {
        StrEvaluate evaluator = new StrEvaluate();
        double result = evaluator.eval(expression);
        assertEquals(expected, result, 0.00001);
    }

}
