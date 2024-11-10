package org.example;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @org.junit.jupiter.api.Test
    void testVariableOperations() {
        Variables variables = new Variables();
        variables.setVariable("x", 31.5);
        variables.setVariable("y", 10.5);
        variables.setVariable("z", -3);
        Calculator calculator = new Calculator(variables);

        assertEquals(39, calculator.evaluate("x + y + z"));
        assertEquals(24, calculator.evaluate("x - y - z"));
        assertEquals(-63, calculator.evaluate("z * (x - y)"));
        assertEquals(-1, calculator.evaluate("x / y / z"));
        assertEquals(-9, calculator.evaluate("x / (y / z)"));
    }

    @org.junit.jupiter.api.Test
    void testMixedOperationsWithVariablesAndNumbers() {
        Variables variables = new Variables();
        variables.setVariable("x", 2);
        variables.setVariable("y", 3.5);
        Calculator calculator = new Calculator(variables);

        assertEquals(0, calculator.evaluate("y * 0 / x"));
        assertEquals(9, calculator.evaluate("x + y * 2"));
        assertEquals(23.33333, calculator.evaluate("x * y / 0.3"));
        assertEquals(3.25, calculator.evaluate("y - 2.5 * x / 4 + 1"));
    }

    @org.junit.jupiter.api.Test
    void testOperationsWithNumbersOnly() {
        Calculator calculator = new Calculator();

        assertEquals(6.28571, calculator.evaluate("2 + 3 * (2 - 4 / 7)"));
    }

    @org.junit.jupiter.api.Test
    void testDivisionByZero() {
        Variables variables = new Variables();
        variables.setVariable("x", 2);
        variables.setVariable("y", 3.5);
        Calculator calculator = new Calculator(variables);

        assertEquals(Double.NaN, calculator.evaluate("x * y / 0"));
        assertEquals(Double.NaN, calculator.evaluate("5 / (0 * y)"));
    }

    @org.junit.jupiter.api.Test
    void testUndefinedVariableUsage() {
        Variables variables = new Variables();
        variables.setVariable("x", 2);
        Calculator calculator = new Calculator(variables);

        assertEquals(Double.NaN, calculator.evaluate("x + y"));
    }

    @org.junit.jupiter.api.Test
    void testVariableDefinedButNotUsed() {
        Variables variables = new Variables();
        variables.setVariable("x", 2);
        variables.setVariable("y", 3.5);
        variables.setVariable("z", -3);
        Calculator calculator = new Calculator(variables);

        assertEquals(3.25, calculator.evaluate("y - 2.5 * x / 4 + 1"));
    }

    @org.junit.jupiter.api.Test
    void testIncorrectBracketPlacement() {
        Variables variables = new Variables();
        variables.setVariable("x", 2);
        variables.setVariable("y", 3.5);
        variables.setVariable("z", -3);
        Calculator calculator = new Calculator(variables);

        assertEquals(Double.NaN, calculator.evaluate("x + y * z)"));
        assertEquals(Double.NaN, calculator.evaluate("x + (y * z"));
        assertEquals(Double.NaN, calculator.evaluate("x + y * z]"));
        assertEquals(Double.NaN, calculator.evaluate("x + [y * z"));
        assertEquals(Double.NaN, calculator.evaluate("x + [y * z]"));
        assertEquals(Double.NaN, calculator.evaluate("x * (100 / [y + z] - 1)"));
        assertEquals(Double.NaN, calculator.evaluate("x * [100 / (y + z] - 1)"));
        assertEquals(Double.NaN, calculator.evaluate("x * (100 / ([)y + z] - 1)"));
    }
}