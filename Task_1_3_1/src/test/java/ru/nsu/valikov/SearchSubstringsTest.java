package ru.nsu.valikov;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class for testing code.
 */
class SearchSubstringsTest {
    @Test
    void twelveKbTest() {
        String file = "12kb.txt";
        List<Integer> expectedList = Arrays.asList(3893, 4900, 7551, 8598, 10090, 10665);
        List<Integer> testList = new SearchSubstrings().find(file, "Podrick");
        Assertions.assertEquals(expectedList, testList);
    }

    @Test
    void emptyFileTest() {
        String file = "./empty.txt";
        List<Integer> testList = new SearchSubstrings().find(file, "zeleeboba");
        List<Integer> expectedList = new ArrayList<>();
        Assertions.assertEquals(expectedList, testList);
    }

    @Test
    void emptyPatternTest() {
        String file = "./12kb.txt";
        List<Integer> testList = new SearchSubstrings().find(file, "");
        List<Integer> expectedList = new ArrayList<>();
        Assertions.assertEquals(expectedList, testList);
    }

    @Test
    void tenMbTest() {
        String file = "./tenmb.txt";
        List<Integer> testList = new SearchSubstrings().find(file, "aboba");
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(3455370);
        Assertions.assertEquals(expectedList, testList);
    }

    @Test
    void twentyThreeKbTest() {
        String file = "./23kb.txt";
        List<Integer> expectedList =
                Arrays.asList(5568, 7060, 9727, 10423, 11196, 11260, 14056, 18184, 19106, 19511);
        List<Integer> testList = new SearchSubstrings().find(file, "Maester");
        Assertions.assertEquals(expectedList, testList);
    }

    @Test
    void pirogTest() {
        String file = "./sok.txt";
        List<Integer> expectedList = List.of(7);
        List<Integer> testList = new SearchSubstrings().find(file, "пирог");
        Assertions.assertEquals(expectedList, testList);
    }


    private void createBigFile() {
        String path = "./sok.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        try (PrintWriter writer = new PrintWriter(
                Objects.requireNonNull(classLoader.getResource(path)).getPath())) {
            Random rand = new Random();
            for (long i = 0L; i < Long.parseLong("21474836480"); i++) {
                if (i == 1234567890) {
                    writer.write("NSU{f1nDM3}");
                }
                writer.write(rand.nextInt(60) + 'A');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void bigTest() {
        createBigFile();
        String file = "sok.txt";
        List<Integer> expectedList = List.of(1234567890);
        List<Integer> testList = new SearchSubstrings().find(file, "NSU{f1nDM3}");
        System.out.println(testList);
        Assertions.assertEquals(expectedList, testList);
    }

}