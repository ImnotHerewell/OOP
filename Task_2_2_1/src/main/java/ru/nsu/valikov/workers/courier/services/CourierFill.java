package ru.nsu.valikov.workers.courier.services;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.valikov.orders.Order;
import ru.nsu.valikov.orders.services.popfortime.PoperForTime;
import ru.nsu.valikov.utils.LoggerHelper;

/**
 * Class, where the courier tries to fill his/her/its/their/helicopters trunk.
 */
public class CourierFill {
    private int trunkCapacity;
    private final PoperForTime stock;
    private final Set<Order> takenOrders; // TreeSet
    private static final int SECONDS_TO_WAIT = 10;
    private static final Logger logger = LogManager.getLogger(CourierFill.class.getName());

    /**
     * Default constructor, AGAIN.
     *
     * @param trunkCapacity how much pizzaz courier can carry
     * @param stock         pizzeria stock
     * @param takenOrders   hmm, orders that courier must deliver
     */
    public CourierFill(int trunkCapacity, PoperForTime stock, Set<Order> takenOrders) {
        this.trunkCapacity = trunkCapacity;
        this.stock = stock;
        this.takenOrders = takenOrders;
    }

    public void fill() throws InterruptedException {
        while (trunkCapacity-- > 0) {
            Order order = stock.popForTime(SECONDS_TO_WAIT);
            if (order == null) {
                return;
            }
            final int orderId = order.getOrderId();
            String loggerMessage = LoggerHelper.messageWithOrderId(orderId);
            logger.info(loggerMessage + "has taken by courier.");
            takenOrders.add(order);
        }
    }
}
