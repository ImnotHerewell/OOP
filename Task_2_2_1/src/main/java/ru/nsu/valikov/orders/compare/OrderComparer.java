package ru.nsu.valikov.orders.compare;

import static ru.nsu.valikov.orders.Order.MAXCOORDINATE;

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
        return order.getPseudoLatitude() * MAXCOORDINATE + order.getPseudoLongitude();
    }

}
