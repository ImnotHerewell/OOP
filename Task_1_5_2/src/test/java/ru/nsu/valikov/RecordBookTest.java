package ru.nsu.valikov;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RecordBookTest {

    @Test
    void addRecordTest() throws NoSuchFieldException, IllegalAccessException {
        RecordBook.main(new String[]{"-add", "Моя заметка", "Очень важная заметка"});

        var map = RecordBook.class.getDeclaredField("records");
        map.setAccessible(true);
        TreeMap<LocalDateTime, Record> testMap = (TreeMap<LocalDateTime, Record>) map.get(map);
        Assertions.assertTrue(testMap.containsValue(new Record("Моя заметка", "Очень важная заметка")));
    }

    @Test
    void mainTest() {
    }
}