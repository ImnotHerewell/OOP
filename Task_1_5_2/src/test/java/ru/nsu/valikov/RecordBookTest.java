package ru.nsu.valikov;

import java.time.LocalDateTime;
import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
class RecordBookTest {
    //    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    //    private final PrintStream originalOut = System.out;
    //    private final PrintStream originalErr = System.err;

    @Test
    void addRecordTest() throws NoSuchFieldException, IllegalAccessException {
        RecordBook.main(new String[]{"-add", "Моя заметка", "Очень важная заметка"});

        var map = RecordBook.class.getDeclaredField("records");
        map.setAccessible(true);
        @SuppressWarnings("unchecked") var testMap = (TreeMap<LocalDateTime, Record>) map.get(map);
        Assertions.assertTrue(
                testMap.containsValue(new Record("Моя заметка", "Очень важная заметка")));
    }

    @Test
    void removeRecordTest() throws NoSuchFieldException, IllegalAccessException {
        RecordBook.main(new String[]{"-add", "Моя заметка2", "Очень важная заметка2"});
        var map = RecordBook.class.getDeclaredField("records");
        map.setAccessible(true);
        @SuppressWarnings("unchecked") var testMap = (TreeMap<LocalDateTime, Record>) map.get(map);
        Assertions.assertTrue(
                testMap.containsValue(new Record("Моя заметка2", "Очень важная заметка2")));

        RecordBook.main(new String[]{"-rm", "Моя заметка2"});
        map = RecordBook.class.getDeclaredField("records");
        map.setAccessible(true);
        @SuppressWarnings("unchecked") TreeMap<LocalDateTime, Record> testMap2 =
                (TreeMap<LocalDateTime, Record>) map.get(map);
        Assertions.assertFalse(
                testMap2.containsValue(new Record("Моя заметка2", "Очень важная заметка2")));
    }

    @Test
    void showTest() throws NoSuchFieldException, IllegalAccessException {
        RecordBook.main(new String[]{"-add", "Моя заметка2", "Очень важная заметка2"});
        RecordBook.main(new String[]{"-add", "some bullshit1", "some bullshit2"});
        var map = RecordBook.class.getDeclaredField("records");
        map.setAccessible(true);
        @SuppressWarnings("unchecked") var testMap = (TreeMap<LocalDateTime, Record>) map.get(map);
    }
}