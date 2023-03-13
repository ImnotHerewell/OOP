package ru.nsu.valikov.utils;

/**
 * Order status for logging and performing reading code without magic strings.
 */
public enum OrderStatus {
    ACCEPTED,
    WAITING_FOR_COOKING,
    COOKING,
    COOKED,
    WAITING_FOR_DELIVERING,
    TAKEN_BY_COURIER,
    AT_DELIVERING,
    DELIVERED;
}
