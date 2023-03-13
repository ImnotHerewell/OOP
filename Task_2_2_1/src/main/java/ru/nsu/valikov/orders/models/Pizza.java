package ru.nsu.valikov.orders.models;

import lombok.Getter;
import ru.nsu.valikov.orders.Order;

/**
 * Pizza object, what customers buy.
 */
public class Pizza extends Order{
    @Getter
    private final int pizzaId;

    /**
     * Constructor for amateurs.
     *
     * @param pizzaId         🍕
     * @param pseudoLatitude  🌐
     * @param pseudoLongitude 🌐
     */
    public Pizza(int pizzaId, int pseudoLatitude, int pseudoLongitude) {
        super(pseudoLatitude, pseudoLongitude);
        this.pizzaId = pizzaId;
    }

    /**
     * When constructor annoys.
     *
     * @param pizzaId         🍕
     * @param pseudoLatitude  🌐
     * @param pseudoLongitude 🌐
     *
     * @return new Pizza
     */
    public static Pizza of(int pizzaId, int pseudoLatitude, int pseudoLongitude) {
        return new Pizza(pizzaId, pseudoLatitude, pseudoLongitude);
    }
}
