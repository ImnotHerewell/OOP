package ru.nsu.valikov.utils;

/**
 * Souts everything.
 */
public class LoggerHelper {
    public static final StringBuilder STANDARD_LOGGER_MESSAGE = new StringBuilder("Pizza with id ");

    private LoggerHelper() {
        throw new AssertionError();
    }

    public static String messageWithOrderId(int orderId) {
        return (STANDARD_LOGGER_MESSAGE + String.valueOf(orderId) + " ");
    }
}
