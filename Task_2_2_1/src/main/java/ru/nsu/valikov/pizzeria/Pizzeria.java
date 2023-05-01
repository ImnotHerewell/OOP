package ru.nsu.valikov.pizzeria;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.nsu.valikov.workers.chef.Chef;
import ru.nsu.valikov.workers.courier.Courier;

/**
 * Father for all pizzeriaz.
 */
@AllArgsConstructor
public abstract class Pizzeria {
    @Getter
    private final List<Courier> couriers;
    @Getter
    private final List<Chef> workers;
}
