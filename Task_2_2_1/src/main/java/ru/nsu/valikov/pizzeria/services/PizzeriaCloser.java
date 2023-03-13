package ru.nsu.valikov.pizzeria.services;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import ru.nsu.valikov.orders.Order;
import ru.nsu.valikov.pizzeria.Pizzeria;
import ru.nsu.valikov.workers.chef.Chef;
import ru.nsu.valikov.workers.courier.Courier;

/**
 * Close a pizzeria.
 */
public class PizzeriaCloser extends Pizzeria implements Close {
    private final BlockingQueue<Order> orders;
    private final BlockingQueue<Order> stock;

    /**
     * Default constructor for destroying pizzeria.
     *
     * @param couriers which couriers should be fired
     * @param workers  which pizzeria workers should be fired
     * @param orders   which orders should be canceled
     * @param stock    which pizzaz should be destroyed
     */
    public PizzeriaCloser(List<Courier> couriers, List<Chef> workers, BlockingQueue<Order> orders,
                          BlockingQueue<Order> stock) {
        super(couriers, workers);
        this.orders = orders;
        this.stock = stock;
    }

    @Override
    public void close() {
        couriers.forEach(Thread::interrupt);
        workers.forEach(Thread::interrupt);
        couriers.clear();
        workers.clear();
        orders.clear();
        stock.clear();
    }
}
