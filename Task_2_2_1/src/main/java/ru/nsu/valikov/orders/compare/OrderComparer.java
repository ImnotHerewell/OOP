package ru.nsu.valikov.orders.compare;

import static ru.nsu.valikov.orders.Order.MAX_COORDINATE;

import ru.nsu.valikov.orders.Order;

/**
 * Calculates numeric value for order object.
 */
public class OrderComparer implements Calculable {
    private final Order order;

    public OrderComparer(Order order) {
        this.order = order;
    }

    @Override
    public int compareCode() {
        return order.getPseudoLatitude() * MAX_COORDINATE + order.getPseudoLongitude();
    }
}
