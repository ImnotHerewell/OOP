package ru.nsu.valikov.utils;

/**
 * Order status for logging and performing reading code without magic strings.
 */
public enum OrderStatus {
    ACCEPTED,
    WAITINGFORCOOKING,
    COOKING,
    COOKED,
    WAITINGFORDELIVERING,
    TAKENBYCOURIER,
    DELIVERING,
    DELIVERED;

    @Override
    public String toString() {
        return super.toString();
    }
}
