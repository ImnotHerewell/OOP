package ru.nsu.valikov.orders.services.pop;

import java.util.concurrent.BlockingQueue;
import ru.nsu.valikov.orders.Order;
import ru.nsu.valikov.orders.services.QueueService;

/**
 * Pop object from orders queue.
 */
public class PopService extends QueueService implements Poper {

    public PopService(BlockingQueue<Order> queue) throws InterruptedException {
        super(queue);
    }

    @Override
    public Order pop() throws InterruptedException {
        return super.getQueue().take();
    }
}
