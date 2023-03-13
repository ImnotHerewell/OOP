package ru.nsu.valikov.orders.models;

import ru.nsu.valikov.orders.Order;

/**
 * Pizza object, what customers buy.
 */
public class Pizza extends Order {
    private final int pizzaId;

    public Pizza(int pizzaId, int pseudoLatitude, int pseudoLongitude) {
        super(pseudoLatitude, pseudoLongitude);
        this.pizzaId = pizzaId;
    }

    public int getPizzaId() {
        return pizzaId;
    }
}
