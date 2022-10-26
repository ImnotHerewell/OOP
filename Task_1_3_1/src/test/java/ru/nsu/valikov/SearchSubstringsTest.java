package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Default class with tests.
 */
class SearchSubstringsTest {
    /**
     * Random test with 12 kb file.
     */
    @Test
    void twelveKbTest() {
        String file = "12kb.txt";
        List<Integer> expectedList = Arrays.asList(3893, 4900, 7551, 8598, 10090, 10665);
        List<Integer> testList = new SearchSubstrings().find(file, "Podrick");
        Assertions.assertEquals(expectedList, testList);
    }

    /**
     * Test with empty file.
     */
    @Test
    void emptyFileTest() {
        String file = "./empty.txt";
        List<Integer> testList = new SearchSubstrings().find(file, "zeleeboba");
        List<Integer> expectedList = new ArrayList<>();
        Assertions.assertEquals(expectedList, testList);
    }

    /**
     * Test with empty pattern-substring.
     */
    @Test
    void emptyPatternTest() {
        String file = "./12kb.txt";
        List<Integer> testList = new SearchSubstrings().find(file, "");
        List<Integer> expectedList = new ArrayList<>();
        Assertions.assertEquals(expectedList, testList);
    }

    /**
     * Random test with 10 mb file.
     */
    @Test
    void tenMbTest() {
        String file = "./tenmb.txt";
        List<Integer> testList = new SearchSubstrings().find(file, "aboba");
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(3455370);
        Assertions.assertEquals(expectedList, testList);
    }

    /**
     * Random test with 23 kb file.
     */
    @Test
    void twentyThreeKbTest() {
        String file = "./23kb.txt";
        List<Integer> expectedList = Arrays.asList(5568, 7060, 9727, 10423, 11196, 11260, 14056,
                                                   18184, 19106, 19511);
        List<Integer> testList = new SearchSubstrings().find(file, "Maester");
        Assertions.assertEquals(expectedList, testList);
    }

    /**
     * Example pirog test.
     */
    @Test
    void pirogTest() {
        String file = "./sok.txt";
        List<Integer> expectedList = List.of(7);
        List<Integer> testList = new SearchSubstrings().find(file, "пирог");
        Assertions.assertEquals(expectedList, testList);
    }

}