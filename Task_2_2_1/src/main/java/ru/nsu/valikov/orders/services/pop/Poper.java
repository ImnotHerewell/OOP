package ru.nsu.valikov.orders.services.pop;

import ru.nsu.valikov.orders.Order;

/**
 * Perform allowing to pop objects (from stacks).
 */
public interface Poper {
    Order pop() throws InterruptedException;
}
