package ru.nsu.valikov.utils;

/**
 * Souts everything.
 */
public class LoggerHelper {
    private static final String STANDARD_LOGGER_MESSAGE = "Pizza with id ";

    private LoggerHelper() {
        throw new AssertionError();
    }

    public static String messageWithOrderId(int orderId) {
        return STANDARD_LOGGER_MESSAGE + String.valueOf(orderId) + " ";
    }
}
