package ru.nsu.valikov.orders.services.popfortime;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import ru.nsu.valikov.orders.Order;
import ru.nsu.valikov.orders.services.QueueService;

/**
 * Waiting performs for 10 milliseconds.
 */
public class PopForTimeService extends QueueService implements PoperForTime {
    public PopForTimeService(BlockingQueue<Order> queue) {
        super(queue);
    }

    @Override
    public Order popForTime(int milli) throws InterruptedException {
        return super.getQueue().poll(milli * 10L, TimeUnit.MILLISECONDS);
    }
}
