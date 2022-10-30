package ru.nsu.valikov;

import java.util.Locale;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Combinations with degree and numbers.
 */
public class CombinationsTest {
    public static final double EPS = 1e-7;
    Calculator calc = new Calculator();
    Scanner scanner;
    String res;

    @Test
    void randomYtubeTest() {
        res = calc.parser("./combination/randomYtubeExample.txt").replace("i", "")
                  .replace("-", " -").replace("+", " +");
        scanner = new Scanner(res).useLocale(Locale.US);
        double resD = scanner.nextDouble();
        double expD = 28;
        Assertions.assertEquals(expD, resD, EPS);
    }

    @Test
    void complexAndDegree() {
        res = calc.parser("./combination/complexAndDegree.txt").replace("i", "").replace("-", " -")
                  .replace("+", " +");
        scanner = new Scanner(res).useLocale(Locale.US);
        double resD = scanner.nextDouble();
        double imD = scanner.nextDouble();
        double expRe = 2.8245749006236326200723797955493;
        double expIm = 70;
        Assertions.assertEquals(expRe, resD, EPS);
        Assertions.assertEquals(expIm, imD, EPS);
    }
}
