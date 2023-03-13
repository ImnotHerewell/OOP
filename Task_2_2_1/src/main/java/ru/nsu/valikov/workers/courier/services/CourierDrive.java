package ru.nsu.valikov.workers.courier.services;

import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.valikov.orders.Order;
import ru.nsu.valikov.orders.compare.OrderComparer;
import ru.nsu.valikov.orders.models.Pizza;
import ru.nsu.valikov.utils.LoggerHelper;

/**
 * Class, where the courier tries to deliver all taken orders to clients.
 */
public class CourierDrive {
    private final Set<Order> takenOrders;
    private static final int IMAGINE_PIZZA_ID = -1;
    private static final int IMAGINE_COORDINATE = 0;
    private static final int START_COMPARE_VALUE = 0;
    private static final Logger logger = LogManager.getLogger(CourierDrive.class.getName());

    public CourierDrive(Set<Order> takenOrders) {
        this.takenOrders = takenOrders;
    }


    public void drive() throws InterruptedException {
        if (takenOrders.isEmpty()) {
            return;
        }
        List<Order> orders = takenOrders.stream().toList();
        int currentOrderComparer = START_COMPARE_VALUE;
        int newOrderComparer;
        for (int orderIndex = 0; orderIndex < orders.size(); orderIndex++) {
            Order order = orders.get(orderIndex);
            final int orderId = order.getOrderId();
            String loggerMessage = LoggerHelper.messageWithOrderId(orderId);
            logger.info(loggerMessage + "is delivering.");
            currentOrderComparer = new OrderComparer(orders.get(orderIndex)).compareCode();
            newOrderComparer = new OrderComparer(
                    orderIndex == 0 ? new Pizza(IMAGINE_PIZZA_ID, IMAGINE_COORDINATE,
                                                IMAGINE_COORDINATE)
                                    : orders.get(orderIndex - 1)).compareCode();
            Thread.sleep(Math.abs(currentOrderComparer - newOrderComparer) / Order.MAX_COORDINATE
                         + Math.abs(currentOrderComparer - newOrderComparer)
                           % Order.MAX_COORDINATE);
            logger.info(loggerMessage + "has delivered.");
        }
        Thread.sleep(currentOrderComparer / Order.MAX_COORDINATE
                     + currentOrderComparer % Order.MAX_COORDINATE);
    }
}
