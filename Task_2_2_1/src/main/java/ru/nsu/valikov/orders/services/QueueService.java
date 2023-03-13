package ru.nsu.valikov.orders.services;

import java.util.concurrent.BlockingQueue;
import lombok.Getter;
import ru.nsu.valikov.orders.Order;

/**
 * Father for all queues.
 */
public abstract class QueueService {
    @Getter
    private final BlockingQueue<Order> queue;

    protected QueueService(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

}
