package ru.nsu.valikov;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

/**
 * Define thread that we used.
 */
public class PrimeThread implements Callable<Boolean> {
    private final List<Integer> listOfIntegers;
    private final int checkNumberStart;
    private final int checkNumberEnd;

    /**
     * Constructor for thread.
     *
     * @param listOfIntegers   list of numbers that we want to check
     * @param checkNumberStart index of the first checking number
     * @param checkNumberEnd   index of the lasts checking number
     */
    public PrimeThread(List<Integer> listOfIntegers, final int checkNumberStart,
                       final int checkNumberEnd) {
        this.listOfIntegers = listOfIntegers;
        this.checkNumberStart = checkNumberStart;
        this.checkNumberEnd = checkNumberEnd;
    }

    /**
     * Check numbers on primality in range(checkNumberStart, checkNumberEnd + 1).
     */
    @Override
    public Boolean call() {
        return IntStream.range(checkNumberStart, checkNumberEnd).allMatch(
                number -> BigInteger.valueOf(listOfIntegers.get(number)).isProbablePrime(100));
    }


}
