package ru.nsu.valikov;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void parserTest() {
        Calculator calc = new Calculator();
        System.out.println(calc.parser("example.txt").getValue());
    }
}