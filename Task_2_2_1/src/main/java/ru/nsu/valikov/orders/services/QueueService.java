package ru.nsu.valikov.orders.services;

import java.util.concurrent.BlockingQueue;
import ru.nsu.valikov.orders.Order;

/**
 * Father for all queues.
 */
public abstract class QueueService {
    private final BlockingQueue<Order> queue;

    protected QueueService(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public BlockingQueue<Order> getQueue() {
        return queue;
    }
}
