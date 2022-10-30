package ru.nsu.valikov;

import java.util.Locale;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ComplexNumberTest {

    private final static double EPS = 1e-7;
    Calculator calc = new Calculator();
    Scanner scanner;
    String res;

    @Test
    void plus() {
        res = calc.parser("./complex/plus.txt").replace("i", "").replace("-", " -").replace("+",
                                                                                            " +");
        scanner = new Scanner(res).useLocale(Locale.US);
        double resRe = scanner.nextDouble();
        double resIm = scanner.nextDouble();
        double expRe = 2.14;
        double expIm = -10.82;
        Assertions.assertEquals(expRe, resRe, EPS);
        Assertions.assertEquals(expIm, resIm, EPS);
    }

    @Test
    void minus() {
    }

    @Test
    void multiplication() {
    }

    @Test
    void division() {
    }

    @Test
    void pow() {
    }

    @Test
    void log() {
    }

    @Test
    void sqrt() {
    }

    @Test
    void sin() {
        res = calc.parser("./complex/sin.txt");
        double resD = Double.parseDouble(res);
        double expD = 0.82687954053200256025588742910922;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void cos() {
        res = calc.parser("./complex/cos.txt");
        double resD = Double.parseDouble(res);
        double expD = -0.41614683654714238699756822950076;
        Assertions.assertEquals(expD, resD, EPS);
    }
}