package ru.nsu.valikov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void parserTest() {
        Calculator calc=new Calculator();
        calc.parser("example.txt");
    }
}