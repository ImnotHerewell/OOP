package com.example.heapsort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main class with tests.
 */
public class MainHeapSortTest {

    /**
     * Descending sorted list.
     */
    @Test
    public void heapSortListReverse() {
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        MainHeapSort.heapSort(test);
        Assertions.assertEquals(expectedResult, test);
    }

    /**
     * Ascending sorted list.
     */
    @Test
    public void heapSortListSimple() {
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        MainHeapSort.heapSort(test);
        Assertions.assertEquals(expectedResult, test);
    }

    /**
     * List with random numbers №1.
     */
    @Test
    public void heapSortListRandom1() {
        ArrayList<Integer> test =
                new ArrayList<>(
                        Arrays.asList(12900, 3131, -3113, 64921, 3441,
                                45688, -2674, 45411, 91513, 6102));
        ArrayList<Integer> expectedResult =
                new ArrayList<>(
                        Arrays.asList(-3113, -2674, 3131, 3441, 6102,
                                12900, 45411, 45688, 64921, 91513));
        MainHeapSort.heapSort(test);
        Assertions.assertEquals(expectedResult, test);
    }

    /**
     * List with random numbers №2.
     */
    @Test
    public void heapSortListRandom2() {
        ArrayList<Integer> test =
                new ArrayList<>(
                        Arrays.asList(2549, 51754, 62807, 64991, 15837,
                                58694, 67632, 89720, 13508, 62430));
        ArrayList<Integer> expectedResult =
                new ArrayList<>(
                        Arrays.asList(2549, 13508, 15837, 51754, 58694,
                                62430, 62807, 64991, 67632, 89720));
        MainHeapSort.heapSort(test);
        Assertions.assertEquals(expectedResult, test);
    }

    /**
     * List with random numbers №3.
     */
    @Test
    public void heapSortListRandom3() {
        ArrayList<Integer> test =
                new ArrayList<>(
                        Arrays.asList(74267, 90161, 88094, -4032, 15297,
                                56379, -4069, 29600, 21380, 30568));
        ArrayList<Integer> expectedResult =
                new ArrayList<>(
                        Arrays.asList(-4069, -4032, 15297, 21380, 29600,
                                30568, 56379, 74267, 88094, 90161));
        MainHeapSort.heapSort(test);
        Assertions.assertEquals(expectedResult, test);
    }

    /**
     * List with repeating numbers.
     */
    @Test
    public void heapSortListWithRepeatingNumbers() {
        ArrayList<Integer> test =
                new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, -150,
                        1654, 145, 47, -356, 200, -698));
        ArrayList<Integer> expectedResult =
                new ArrayList<>(Arrays.asList(-698, -356, -150, 1, 1, 1,
                        1, 1, 1, 1, 47, 145, 200, 1654));
        MainHeapSort.heapSort(test);
        Assertions.assertEquals(expectedResult, test);
    }

    /**
     * Some random siftDown test.
     */
    @Test
    void siftDownTest() {
        ArrayList<Integer> test =
                new ArrayList<>(
                        Arrays.asList(12900, 3131, -3113, 64921, 3441,
                                45688, -2674, 45411, 91513, 6102));
        ArrayList<Integer> expectedResult =
                new ArrayList<>(
                        Arrays.asList(-3113, 3131, -2674, 64921, 3441,
                                45688, 12900, 45411, 91513, 6102));
        MainHeapSort.siftDown(test, 10, 0);
        Assertions.assertEquals(expectedResult, test);
    }
}
