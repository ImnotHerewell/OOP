package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private static long time;

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

    @BeforeEach
    public void timeCalculation() {
        time = System.nanoTime();
    }

    @AfterEach
    public void timePrint() {
        System.out.println(System.nanoTime() - time);
    }

    @Test
    void checkTenSerial() {
        Assertions.assertTrue(testTen.serialCheck());
    }

    @Test
    void checkTenParallel() {
        Assertions.assertTrue(testTen.parallelCheck());
    }

    @Test
    void checkTenThreadsThree() throws InterruptedException {
        Assertions.assertTrue(testTen.threadsCheck(3));
    }

    @Test
    void checkTenThreadsFive() throws InterruptedException {
        Assertions.assertTrue(testTen.threadsCheck(5));
    }

    @Test
    void checkTenThreadsTen() throws InterruptedException {
        Assertions.assertTrue(testTen.threadsCheck(10));
    }

    @Test
    void checkTenThousandsSerial() {
        Assertions.assertTrue(testTenThousands.serialCheck());
    }

    @Test
    void checkTenThousandsParallel() {
        Assertions.assertTrue(testTenThousands.parallelCheck());
    }

    @Test
    void checkTenThousandsThreadsThree() throws InterruptedException {
        Assertions.assertTrue(testTenThousands.threadsCheck(3));
    }

    //255058200
    //297380300
    @Test
    void checkTenThousandsThreadsFive() throws InterruptedException {
        Assertions.assertTrue(testTenThousands.threadsCheck(5));
    }

    @Test
    void checkTenThousandsThreadsTen() throws InterruptedException {
        Assertions.assertTrue(testTenThousands.threadsCheck(10));
    }

    @Test
    void checkTenThousandsThreadsTwenty() throws InterruptedException {
        Assertions.assertTrue(testTenThousands.threadsCheck(20));
    }

    @Test
    void checkTenThousandsThreadsForty() throws InterruptedException {
        Assertions.assertTrue(testTenThousands.threadsCheck(40));
    }

    @Test
    void checkHundredSerial() {
        Assertions.assertTrue(testHundred.serialCheck());
    }

    @Test
    void checkHundredParallel() {
        Assertions.assertTrue(testHundred.parallelCheck());
    }

    @Test
    void checkHundredThreadsThree() throws InterruptedException {
        Assertions.assertTrue(testHundred.threadsCheck(3));
    }

    @Test
    void checkHundredThreadsFive() throws InterruptedException {
        Assertions.assertTrue(testHundred.threadsCheck(5));
    }

    @Test
    void checkHundredThreadsTen() throws InterruptedException {
        Assertions.assertTrue(testHundred.threadsCheck(10));
    }

    @Test
    void checkHundredThreadsTwenty() throws InterruptedException {
        Assertions.assertTrue(testHundred.threadsCheck(20));
    }

    @Test
    void checkHundredThreadsForty() throws InterruptedException {
        Assertions.assertTrue(testHundred.threadsCheck(40));
    }

    @Test
    void checkThousandSerial() {
        Assertions.assertTrue(testThousand.serialCheck());
    }

    @Test
    void checkThousandParallel() {
        Assertions.assertTrue(testThousand.parallelCheck());
    }

    @Test
    void checkThousandThreadsThree() throws InterruptedException {
        Assertions.assertTrue(testThousand.threadsCheck(3));
    }

    @Test
    void checkThousandThreadsFive() throws InterruptedException {
        Assertions.assertTrue(testThousand.threadsCheck(5));
    }

    @Test
    void checkThousandThreadsTen() throws InterruptedException {
        Assertions.assertTrue(testThousand.threadsCheck(10));
    }

    @Test
    void checkThousandThreadsTwenty() throws InterruptedException {
        Assertions.assertTrue(testThousand.threadsCheck(20));
    }

    @Test
    void checkThousandThreadsForty() throws InterruptedException {
        Assertions.assertTrue(testThousand.threadsCheck(40));
    }

    @Test
    void checkHundredThousandsSerial() {
        Assertions.assertTrue(testHundredThousands.serialCheck());
    }

    @Test
    void checkHundredThousandsParallel() {
        Assertions.assertTrue(testHundredThousands.parallelCheck());
    }

    @Test
    void checkHundredThousandsThreadsThree() throws InterruptedException {
        Assertions.assertTrue(testHundredThousands.threadsCheck(3));
    }

    @Test
    void checkHundredThousandsThreadsFive() throws InterruptedException {
        Assertions.assertTrue(testHundredThousands.threadsCheck(5));
    }

    @Test
    void checkHundredThousandsThreadsTen() throws InterruptedException {
        Assertions.assertTrue(testHundredThousands.threadsCheck(10));
    }

    @Test
    void checkHundredThousandsThreadsTwenty() throws InterruptedException {
        Assertions.assertTrue(testHundredThousands.threadsCheck(20));
    }

    @Test
    void checkHundredThousandsThreadsForty() throws InterruptedException {
        Assertions.assertTrue(testHundredThousands.threadsCheck(40));
    }

    @Test
    void checkFiveMillionSerial() {
        Assertions.assertTrue(testFiveMillion.serialCheck());
    }

    @Test
    void checkFiveMillionParallel() {
        Assertions.assertTrue(testFiveMillion.parallelCheck());
    }

    @Test
    void checkFiveMillionThreadsThree() throws InterruptedException {
        Assertions.assertTrue(testFiveMillion.threadsCheck(3));
    }

    @Test
    void checkFiveMillionThreadsFive() throws InterruptedException {
        Assertions.assertTrue(testFiveMillion.threadsCheck(5));
    }

    @Test
    void checkFiveMillionThreadsTen() throws InterruptedException {
        Assertions.assertTrue(testFiveMillion.threadsCheck(10));
    }

    @Test
    void checkFiveMillionThreadsTwenty() throws InterruptedException {
        Assertions.assertTrue(testFiveMillion.threadsCheck(20));
    }

    @Test
    void checkFiveMillionThreadsForty() throws InterruptedException {
        Assertions.assertTrue(testFiveMillion.threadsCheck(40));
    }
}
