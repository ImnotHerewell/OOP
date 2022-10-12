package com.example.heapsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Main class with tests.
 */
public class MainHeapSortTest {
    /**
     * Test with an empty list.
     */
    @Test
    public void heapSortEmptyList() {
        List<Integer> test =
                new ArrayList<>(0);
        List<Integer> expectedResult =
                new ArrayList<>(0);
        Collections.sort(expectedResult);
        MainHeapSort.heapSort(test);
        Assertions.assertEquals(expectedResult, test);
    }

    /**
     * Tests with only one integer.
     */
    @Test
    public void heapSortOneRandomInteger() {
        Random random = new Random();
        for (int countI = 0; countI < 100; countI++) {
            List<Integer> test =
                    new ArrayList<>(1);
            List<Integer> expectedResult =
                    new ArrayList<>(1);
            for (int indexI = 0; indexI < 1; indexI++) {
                test.add(random.nextInt(10000));
            }
            expectedResult.addAll(test);
            Collections.sort(expectedResult);
            MainHeapSort.heapSort(test);
            Assertions.assertEquals(expectedResult, test);
        }
    }

    /**
     * List with random numbers.
     */
    @Test
    public void heapSortListRandom() {
        Random random = new Random();
        for (int countI = 0; countI < 100; countI++) {
            List<Integer> test =
                    new ArrayList<>(100);
            List<Integer> expectedResult =
                    new ArrayList<>(100);
            for (int indexI = 0; indexI < 100; indexI++) {
                test.add(random.nextInt(10000));
            }
            expectedResult.addAll(test);
            Collections.sort(expectedResult);
            MainHeapSort.heapSort(test);
            Assertions.assertEquals(expectedResult, test);
        }
    }

    /**
     * Some random siftDown test.
     */
    @Test
    void siftDownTest() {
        List<Integer> test =
                new ArrayList<>(
                        Arrays.asList(12900, 3131, -3113, 64921, 3441,
                                45688, -2674, 45411, 91513, 6102));
        List<Integer> expectedResult =
                new ArrayList<>(
                        Arrays.asList(-3113, 3131, -2674, 64921, 3441,
                                45688, 12900, 45411, 91513, 6102));
        MainHeapSort.siftDown(test, 10, 0);
        Assertions.assertEquals(expectedResult, test);
    }
}
