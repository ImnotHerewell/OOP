package ru.nsu.valikov.orders.services.push;

import ru.nsu.valikov.orders.Order;

/**
 * Perform allowing to push objects (to stacks).
 */
public interface Pusher {
    void push(Order order) throws InterruptedException;
}
