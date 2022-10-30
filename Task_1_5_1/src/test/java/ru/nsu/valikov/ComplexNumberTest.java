package ru.nsu.valikov;

import java.util.Locale;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ComplexNumberTest {

    public static final double EPS = 1e-7;
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
        res = calc.parser("./complex/minus.txt").replace("i", "").replace("-", " -").replace("+",
                                                                                             " +");
        scanner = new Scanner(res).useLocale(Locale.US);
        double resRe = scanner.nextDouble();
        double resIm = scanner.nextDouble();
        double expRe = 0;
        double expIm = -55;
        Assertions.assertEquals(expRe, resRe, EPS);
        Assertions.assertEquals(expIm, resIm, EPS);
    }

    @Test
    void multiplication() {
        res = calc.parser("./complex/multiplication.txt").replace("i", "").replace("-", " -")
                  .replace("+", " +");
        scanner = new Scanner(res).useLocale(Locale.US);
        double resRe = scanner.nextDouble();
        double resIm = scanner.nextDouble();
        double expRe = -47.67;
        double expIm = -294;
        Assertions.assertEquals(expRe, resRe, EPS);
        Assertions.assertEquals(expIm, resIm, EPS);
    }

    @Test
    void division() {
        res = calc.parser("./complex/division.txt").replace("i", "").replace("-", " -").replace("+",
                                                                                                " +")
                  .replace("E -", "E-");
        scanner = new Scanner(res).useLocale(Locale.US);
        double resRe = scanner.nextDouble();
        double resIm = scanner.nextDouble();
        double expRe = 6.3202764e-5;
        double expIm = -0.00015800691;
        Assertions.assertEquals(expRe, resRe, EPS);
        Assertions.assertEquals(expIm, resIm, EPS);
    }

    //Спасибо папашка за тфкп
    @Test
    void pow() {
        res = calc.parser("./complex/pow.txt").replace("i", "").replace("-", " -").replace("+",
                                                                                           " +")
                  .replace("E -", "E-");
        scanner = new Scanner(res).useLocale(Locale.US);
        double resRe = scanner.nextDouble();
        double resIm = scanner.nextDouble();
        double expRe = 2.0785824402196671e37;
        double expIm = 1.1833274102599615e37;
        Assertions.assertEquals(expRe, resRe, EPS);
        Assertions.assertEquals(expIm, resIm, EPS);
    }

    @Test
    void log() {
        res = calc.parser("./complex/log.txt").replace("i", "").replace("-", " -").replace("+",
                                                                                           " +")
                  .replace("E -", "E-");
        scanner = new Scanner(res).useLocale(Locale.US);
        double resRe = scanner.nextDouble();
        double resIm = scanner.nextDouble();
        double expRe = 1.89791984;
        double expIm = 0.642901588;
        Assertions.assertEquals(expRe, resRe, EPS);
        Assertions.assertEquals(expIm, resIm, EPS);
    }

    @Test
    void sqrt() {
        res = calc.parser("./complex/sqrt.txt").replace("i", "").replace("-", " -").replace("+",
                                                                                            " +")
                  .replace("E -", "E-");
        scanner = new Scanner(res).useLocale(Locale.US);
        double resRe = scanner.nextDouble();
        double resIm = scanner.nextDouble();
        double expRe = 13.6806761;
        double expIm = -31.4103629;
        Assertions.assertEquals(expRe, resRe, EPS);
        Assertions.assertEquals(expIm, resIm, EPS);
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