package ru.nsu.valikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests with degrees.
 */
class DegreeTest {
    private static final double EPS = 1e-7;
    private final Calculator calc = new Calculator();
    private String res;
    private String exp;

    @Test
    void plus() {
        res = calc.parser("./degree/plus.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 111718927.83201;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void plusExc() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calc.parser("./degree/plusExc.txt"));
        Assertions.assertEquals("Cannot perform operations with different types.",
                exception.getMessage());
    }

    @Test
    void minus() {
        res = calc.parser("./degree/minus.txt");
        exp = "903.5%";
        Assertions.assertEquals(exp, res);
    }

    @Test
    void minusExc() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calc.parser("./degree/minusExc.txt"));
        Assertions.assertEquals("Cannot perform operations with different types.",
                exception.getMessage());
    }

    @Test
    void multiplication() {
        res = calc.parser("./degree/multiplication.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 104568.45;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void multExc() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calc.parser("./degree/multExc.txt"));
        Assertions.assertEquals("Cannot multiply degrees with degrees or complex numbers.",
                exception.getMessage());
    }

    @Test
    void division() {
        res = calc.parser("./degree/division.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 0.93333333333333333333333333333333;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void divExc() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calc.parser("./degree/divExc.txt"));
        Assertions.assertEquals("Cannot divide degrees by degrees or complex numbers.",
                exception.getMessage());
    }

    @Test
    void pow() {
        res = calc.parser("./degree/pow.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 1.07017235;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void powExc() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calc.parser("./degree/powExc.txt"));
        Assertions.assertEquals(
                "Cannot raising degrees to degrees degree or complex number degree.",
                exception.getMessage());
    }

    @Test
    void log() {
        res = calc.parser("./degree/log.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 4.3307333402863310788434916748042;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void sqrt() {
        res = calc.parser("./degree/sqrt.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 3.1301691601465746331689709983174;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void sin() {
        res = calc.parser("./degree/sin.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 0.5;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void cos() {
        res = calc.parser("./degree/cos.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 0.39073112848927375506208458888909;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void cos90() {
        res = calc.parser("./degree/cos90.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 0;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void sin180() {
        res = calc.parser("./degree/sin180.txt");
        double resD = Double.parseDouble(res.replace("%", ""));
        double expD = 0;
        Assertions.assertEquals(expD, resD, EPS);
    }
}