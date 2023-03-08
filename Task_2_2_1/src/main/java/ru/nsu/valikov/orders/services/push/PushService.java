package ru.nsu.valikov.orders.services.push;

import java.util.concurrent.BlockingQueue;
import ru.nsu.valikov.orders.Order;
import ru.nsu.valikov.orders.services.QueueService;

/**
 * Pushes an order to some queue.
 */
public class PushService extends QueueService implements Pusher {

    public PushService(BlockingQueue<Order> queue) {
        super(queue);
    }

    @Override
    public void push(Order order) throws InterruptedException {
        super.getQueue().put(order);
    }
}
