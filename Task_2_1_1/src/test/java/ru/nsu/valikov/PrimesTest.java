package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Coverage comment.
 */
class PrimesTest {
    /*
    private static final RandomGenerator TEN_GENERATOR = new RandomGenerator(10);
    private static final RandomGenerator HUNDRED_GENERATOR = new RandomGenerator(100);
    private static final RandomGenerator THOUSAND_GENERATOR = new RandomGenerator(1000);
    private static final RandomGenerator TEN_THOUSAND_GENERATOR = new RandomGenerator(10000);
    private static final RandomGenerator HUNDRED_THOUSAND_GENERATOR = new RandomGenerator(100000);
    private static final RandomGenerator MILLION_GENERATOR = new RandomGenerator(1000000);

    private static final Primes randomTestCaseTen = new Primes(TEN_GENERATOR.randomList());
    private static final Primes randomTestCaseHundred = new Primes(HUNDRED_GENERATOR.randomList());
    private static final Primes randomTestCaseThousand =
            new Primes(THOUSAND_GENERATOR.randomList());
    private static final Primes randomTestCaseTenThousand =
            new Primes(TEN_THOUSAND_GENERATOR.randomList());
    private static final Primes randomTestCaseHundredThousand =
            new Primes(HUNDRED_THOUSAND_GENERATOR.randomList());
    private static final Primes randomTestCaseMillion = new Primes(MILLION_GENERATOR.randomList());
    */

    private final Primes testTen = new Primes(readList("10"));
    private final Primes testHundred = new Primes(readList("100"));
    private final Primes testThousand = new Primes(readList("1000"));
    private final Primes testTenThousands = new Primes(readList("10000"));
    private final Primes testHundredThousands = new Primes(readList("100000"));
    private final Primes testFiveMillion = new Primes(readList("Lines_20230202_1675345127403"));

    private List<Integer> readList(String fileName) {
        List<Integer> listOfPrimes = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (Scanner reader = new Scanner(Objects.requireNonNull(
                classLoader.getResourceAsStream("txt/" + fileName + ".txt")))) {
            while (reader.hasNext()) {
                listOfPrimes.add(reader.nextInt());
            }
        }
        return listOfPrimes;
    }

    private void timeCalculation(final long startTime) {
        System.out.println((System.nanoTime() - startTime));
//        return System.nanoTime();
    }

    @Test
    void checkTenSerial() {
        long start = System.nanoTime();
        Assertions.assertTrue(testTen.serialCheck());
        timeCalculation(start);
    }

    @Test
    void checkTenParallel() {
        long start = System.nanoTime();
        Assertions.assertTrue(testTen.parallelCheck());
        timeCalculation(start);
    }

    @Test
    void checkTenThreadsThree() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testTen.threadsCheck(3));
        timeCalculation(start);
    }

    @Test
    void checkTenThreadsFive() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testTen.threadsCheck(5));
        timeCalculation(start);
    }

    @Test
    void checkTenThreadsTen() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testTen.threadsCheck(10));
        timeCalculation(start);
    }

    @Test
    void checkTenThousandsSerial() {
        long start = System.nanoTime();
        Assertions.assertTrue(testTenThousands.serialCheck());
        timeCalculation(start);
    }

    @Test
    void checkTenThousandsParallel() {
        long start = System.nanoTime();
        Assertions.assertTrue(testTenThousands.parallelCheck());
        timeCalculation(start);
    }

    @Test
    void checkTenThousandsThreadsThree() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testTenThousands.threadsCheck(3));
        timeCalculation(start);
    }
//255058200
//297380300
    @Test
    void checkTenThousandsThreadsFive() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testTenThousands.threadsCheck(5));
        timeCalculation(start);
    }

    @Test
    void checkTenThousandsThreadsTen() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testTenThousands.threadsCheck(10));
        timeCalculation(start);
    }

    @Test
    void checkTenThousandsThreadsTwenty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testTenThousands.threadsCheck(20));
        timeCalculation(start);
    }

    @Test
    void checkTenThousandsThreadsForty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testTenThousands.threadsCheck(40));
        timeCalculation(start);
    }

    @Test
    void checkHundredSerial() {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundred.serialCheck());
        timeCalculation(start);
    }

    @Test
    void checkHundredParallel() {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundred.parallelCheck());
        timeCalculation(start);
    }

    @Test
    void checkHundredThreadsThree() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundred.threadsCheck(3));
        timeCalculation(start);
    }

    @Test
    void checkHundredThreadsFive() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundred.threadsCheck(5));
        timeCalculation(start);
    }

    @Test
    void checkHundredThreadsTen() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundred.threadsCheck(10));
        timeCalculation(start);
    }

    @Test
    void checkHundredThreadsTwenty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundred.threadsCheck(20));
        timeCalculation(start);
    }

    @Test
    void checkHundredThreadsForty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundred.threadsCheck(40));
        timeCalculation(start);
    }

    @Test
    void checkThousandSerial() {
        long start = System.nanoTime();
        Assertions.assertTrue(testThousand.serialCheck());
        timeCalculation(start);
    }

    @Test
    void checkThousandParallel() {
        long start = System.nanoTime();
        Assertions.assertTrue(testThousand.parallelCheck());
        timeCalculation(start);
    }

    @Test
    void checkThousandThreadsThree() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testThousand.threadsCheck(3));
        timeCalculation(start);
    }

    @Test
    void checkThousandThreadsFive() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testThousand.threadsCheck(5));
        timeCalculation(start);
    }

    @Test
    void checkThousandThreadsTen() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testThousand.threadsCheck(10));
        timeCalculation(start);
    }

    @Test
    void checkThousandThreadsTwenty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testThousand.threadsCheck(20));
        timeCalculation(start);
    }

    @Test
    void checkThousandThreadsForty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testThousand.threadsCheck(40));
        timeCalculation(start);
    }

    @Test
    void checkHundredThousandsSerial() {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundredThousands.serialCheck());
        timeCalculation(start);
    }

    @Test
    void checkHundredThousandsParallel() {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundredThousands.parallelCheck());
        timeCalculation(start);
    }

    @Test
    void checkHundredThousandsThreadsThree() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundredThousands.threadsCheck(3));
        timeCalculation(start);
    }

    @Test
    void checkHundredThousandsThreadsFive() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundredThousands.threadsCheck(5));
        timeCalculation(start);
    }

    @Test
    void checkHundredThousandsThreadsTen() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundredThousands.threadsCheck(10));
        timeCalculation(start);
    }

    @Test
    void checkHundredThousandsThreadsTwenty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundredThousands.threadsCheck(20));
        timeCalculation(start);
    }

    @Test
    void checkHundredThousandsThreadsForty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testHundredThousands.threadsCheck(40));
        timeCalculation(start);
    }

    @Test
    void checkFiveMillionSerial() {
        long start = System.nanoTime();
        Assertions.assertTrue(testFiveMillion.serialCheck());
        timeCalculation(start);
    }

    @Test
    void checkFiveMillionParallel() {
        long start = System.nanoTime();
        Assertions.assertTrue(testFiveMillion.parallelCheck());
        timeCalculation(start);
    }

    @Test
    void checkFiveMillionThreadsThree() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testFiveMillion.threadsCheck(3));
        timeCalculation(start);
    }

    @Test
    void checkFiveMillionThreadsFive() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testFiveMillion.threadsCheck(5));
        timeCalculation(start);
    }

    @Test
    void checkFiveMillionThreadsTen() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testFiveMillion.threadsCheck(10));
        timeCalculation(start);
    }

    @Test
    void checkFiveMillionThreadsTwenty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testFiveMillion.threadsCheck(20));
        timeCalculation(start);
    }

    @Test
    void checkFiveMillionThreadsForty() throws InterruptedException {
        long start = System.nanoTime();
        Assertions.assertTrue(testFiveMillion.
                                                     threadsCheck(40));
        timeCalculation(start);
    }
}
