package ru.nsu.valikov.orders.services.popfortime;

import ru.nsu.valikov.orders.Order;

/**
 * Waiting on poping occur for specific time.
 */
public interface PoperForTime {
    Order popForTime(int seconds) throws InterruptedException;
}
