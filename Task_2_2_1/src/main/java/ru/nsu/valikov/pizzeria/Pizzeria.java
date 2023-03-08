package ru.nsu.valikov.pizzeria;

import java.util.List;
import ru.nsu.valikov.workers.chef.Chef;
import ru.nsu.valikov.workers.courier.Courier;

/**
 * Father for all pizzeriaz.
 */
public abstract class Pizzeria {
    protected final List<Courier> couriers;
    protected final List<Chef> workers;

    protected Pizzeria(List<Courier> couriers, List<Chef> workers) {
        this.couriers = couriers;
        this.workers = workers;
    }
}
