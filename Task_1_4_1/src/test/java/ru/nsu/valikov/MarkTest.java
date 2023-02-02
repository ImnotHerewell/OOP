package ru.nsu.valikov;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Mark record.
 */
class MarkTest {
    Mark testMark;

    @BeforeEach
    void initialization() {
        testMark = Mark.C;
    }

    @Test
    void getMark() {
        Assertions.assertEquals(testMark.getMark(), 3);
    }

    /**
     * Set retakes count for mark and test if it changes.
     *
     * @throws NoSuchFieldException   if field doesn't exist
     * @throws IllegalAccessException if setAccessible is false
     */
    @Test
    void setRetakeCount() throws NoSuchFieldException, IllegalAccessException {
        testMark.setRetakeCount(2);
        Field field = testMark.getClass().getDeclaredField("retakeCount");
        field.setAccessible(true);
        Assertions.assertEquals(2, field.get(testMark));
    }

    @Test
    void getRetakeCount() {
        Assertions.assertEquals(testMark.getRetakeCount(), 0);
    }
}