package ru.nsu.valikov.orders;

import ru.nsu.valikov.orders.compare.OrderComparer;

/**
 * Father for all types of orders, not just pizza.
 */
public abstract class Order implements Comparable<Order> {
    private static int orderCount = 0;
    private final int orderId;
    private final int pseudoLatitude;
    private final int pseudoLongitude;
    public static final int MAX_COORDINATE = (int) 1E4;

    /**
     * Default constructor, from interesting - it performs increasing of orderId, it is for
     * uniqueness.
     *
     * @param pseudoLatitude  X coordinate on 2D map.
     * @param pseudoLongitude Y coordinate on 2D map.
     */
    public Order(int pseudoLatitude, int pseudoLongitude) {
        this.orderId = ++orderCount;
        this.pseudoLatitude = pseudoLatitude;
        this.pseudoLongitude = pseudoLongitude;
    }

    @Override
    public int compareTo(Order o) {
        OrderComparer thisComparer = new OrderComparer(this);
        OrderComparer objectComparer = new OrderComparer(o);
        return thisComparer.compareCode() - objectComparer.compareCode();
    }

    public int getPseudoLatitude() {
        return pseudoLatitude;
    }

    public int getPseudoLongitude() {
        return pseudoLongitude;
    }

    public int getOrderId() {
        return orderId;
    }
}
