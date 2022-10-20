package ru.nsu.valikov;

import java.util.ArrayList;
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
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(3937);
        expectedList.add(4962);
        expectedList.add(7631);
        expectedList.add(8680);
        expectedList.add(10180);
        expectedList.add(10763);
        List<Integer> testList = new SearchSubstrings(file, "Podrick").getResult();
        testList.sort(Integer::compareTo);
        Assertions.assertEquals(expectedList, testList);
    }

    /**
     * Test with empty file.
     */
    @Test
    void emptyFileTest() {
        String file = "./empty.txt";
        List<Integer> testList = new SearchSubstrings(file, "zeleeboba").getResult();
        List<Integer> expectedList = new ArrayList<>();
        Assertions.assertEquals(expectedList, testList);
    }

    /**
     * Test with empty pattern-substring.
     */
    @Test
    void emptyPatternTest() {
        String file = "./12kb.txt";
        List<Integer> testList = new SearchSubstrings(file, "").getResult();
        List<Integer> expectedList = new ArrayList<>();
        Assertions.assertEquals(expectedList, testList);
    }

    /**
     * Random test with 10 mb file.
     */
    @Test
    void tenMbTest() {
        String file = "./tenmb.txt";
        List<Integer> testList = new SearchSubstrings(file, "aboba").getResult();
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
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(5600);
        expectedList.add(7108);
        expectedList.add(9787);
        expectedList.add(10487);
        expectedList.add(11268);
        expectedList.add(11336);
        expectedList.add(14176);
        expectedList.add(18340);
        expectedList.add(19274);
        expectedList.add(19683);
        List<Integer> testList = new SearchSubstrings(file, "Maester").getResult();
        Assertions.assertEquals(expectedList, testList);
    }

}