package ru.nsu.valikov.workers.courier.services;

import java.util.List;
import java.util.Set;
import ru.nsu.valikov.orders.Order;
import ru.nsu.valikov.orders.compare.OrderComparer;
import ru.nsu.valikov.orders.pizza.Pizza;
import ru.nsu.valikov.utils.Logger;
import ru.nsu.valikov.utils.OrderStatus;

/**
 * Class, where the courier tries to deliver all taken orders to clients.
 */
public class CourierDrive implements Drive {
    private final Set<Order> takenOrders;

    public CourierDrive(Set<Order> takenOrders) {
        this.takenOrders = takenOrders;
    }


    @Override
    public void drive() throws InterruptedException {
        if (takenOrders.isEmpty()) {
            return;
        }
        List<Order> orders = takenOrders.stream().toList();
        int currentOrderComparer = 0;
        int newOrderComparer = 0;
        for (int orderIndex = 0; orderIndex < orders.size(); orderIndex++) {
            Order order = orders.get(orderIndex);
            final int orderId = order.getOrderId();
            Logger logger = new Logger(orderId, OrderStatus.DELIVERING);
            logger.log();
            currentOrderComparer = new OrderComparer(orders.get(orderIndex)).compareCode();
            newOrderComparer = new OrderComparer(orderIndex == 0 ? new Pizza(-1, 0, 0) : orders.get(
                    orderIndex - 1)).compareCode();
            Thread.sleep(Math.abs(currentOrderComparer - newOrderComparer) / Order.MAXCOORDINATE
                         + Math.abs(currentOrderComparer - newOrderComparer) % Order.MAXCOORDINATE);
            logger = new Logger(orderId, OrderStatus.DELIVERED);
            logger.log();
        }
        Thread.sleep(currentOrderComparer / Order.MAXCOORDINATE
                     + currentOrderComparer % Order.MAXCOORDINATE);
    }
}
