package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Generate random numbers, for testing.
 */
public class RandomGenerator {
    private static final Random generator = new Random(777);
    private final int numbersCount;

    public RandomGenerator(int numbersCount) {
        this.numbersCount = numbersCount;
    }

    /**
     * Generate numbersCount non-negative random numbers.
     *
     * @return list with random numbers
     */
    public List<Integer> randomList() {
        List<Integer> numbersList = new ArrayList<>();
        IntStream.range(0, numbersCount)
                 .forEach(index -> numbersList.add(Math.abs(generator.nextInt())));
        return numbersList;
    }
}
