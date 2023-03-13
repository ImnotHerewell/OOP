package ru.nsu.valikov.orders.models;

import lombok.Getter;
import ru.nsu.valikov.orders.Order;

/**
 * Pizza object, what customers buy.
 */
public class Pizza extends Order {
    @Getter
    private final int pizzaId;

    public Pizza(int pizzaId, int pseudoLatitude, int pseudoLongitude) {
        super(pseudoLatitude, pseudoLongitude);
        this.pizzaId = pizzaId;
    }
}
