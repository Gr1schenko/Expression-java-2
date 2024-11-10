package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class VariablesTest {

    @Test
    void setAndGetVariable() {
        Variables variables = new Variables();
        variables.setVariable("Y", 2);
        variables.setVariable("ч", 3.5);
        variables.setVariable("5", -3);

        assertEquals(Double.NaN, variables.getVariable("Y"));
        assertEquals(Double.NaN, variables.getVariable("ч"));
        assertEquals(Double.NaN, variables.getVariable("5"));
    }
}