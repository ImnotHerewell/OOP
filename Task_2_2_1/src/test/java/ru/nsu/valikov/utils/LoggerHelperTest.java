package ru.nsu.valikov.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoggerHelperTest {

    @Test
    void messageWithOrderIdRandomFirst() {
        Assertions.assertEquals(LoggerHelper.messageWithOrderId(777), "Pizza with id 777 ");
    }

    @Test
    void messageWithOrderIdRandomSecond() {
        Assertions.assertEquals(LoggerHelper.messageWithOrderId(0), "Pizza with id 0 ");
    }

    @Test
    void messageWithOrderIdRandomThird() {
        Assertions.assertEquals(LoggerHelper.messageWithOrderId(-777), "Pizza with id -777 ");
    }

}