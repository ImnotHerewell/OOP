package ru.nsu.valikov;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Class for performing primality check.
 */
public class Primes {
    private final List<Integer> listOfIntegers;

    public Primes(List<Integer> listOfIntegers) {
        this.listOfIntegers = listOfIntegers;
    }

    /**
     * Serial check using a stream (same as one thread approach).
     *
     * @return true if all numbers are prime, else false.
     */
    public boolean serialCheck() {
        return listOfIntegers.stream()
                             .allMatch(number -> BigInteger.valueOf(number).isProbablePrime(100));
    }

    /**
     * Parallel check using the parallelStream().
     *
     * @return true if all numbers are prime, else false.
     */
    public boolean parallelCheck() {
        return listOfIntegers.parallelStream()
                             .allMatch(number -> BigInteger.valueOf(number).isProbablePrime(100));
    }

    /**
     * Primality check using threads.
     *
     * @param numberOfThreads how many threads we want to use
     * @return true if all numbers are prime, else false.
     */
    public boolean threadsCheck(final int numberOfThreads) throws InterruptedException {
        final int listSize = listOfIntegers.size();
        if (numberOfThreads > listSize) {
            throw new IllegalArgumentException(
                    "Number of threads must be lower than number of elements.");
        }
        ExecutorService threadsExecutor = Executors.newFixedThreadPool(numberOfThreads);
        List<PrimeThread> threadsList = new ArrayList<>();
        final int listSeparationValue = (listSize + numberOfThreads - 1) / numberOfThreads;
        IntStream.range(0, numberOfThreads).forEach(threadIndex -> threadsList.add(
                new PrimeThread(listOfIntegers, threadIndex * listSeparationValue,
                        Math.min((threadIndex + 1) * listSeparationValue, listSize))));
        List<Future<Boolean>> results = threadsExecutor.invokeAll(threadsList);
        threadsExecutor.shutdown();
        try {
            if (!threadsExecutor.awaitTermination(600, TimeUnit.SECONDS)) {
                threadsExecutor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadsExecutor.shutdownNow();
        }

        return results.stream().allMatch(threadResult -> {
            try {
                return threadResult.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
