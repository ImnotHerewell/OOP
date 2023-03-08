package ru.nsu.valikov.utils;

/**
 * Souts everything.
 */
public class Logger {
    private final int orderId;
    private final OrderStatus status;

    public Logger(int orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }

    public void log() {
        System.out.format("%d is %s\n", orderId, status);
    }
}
