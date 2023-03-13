package ru.nsu.valikov.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

/**
 * Just for reading from JSON.
 */
public class PizzeriaSystem {
    @Getter
    private final List<Integer> chefsWorkEfficiency;
    @Getter
    private final List<Integer> couriersTrunkCapacity;
    @Getter
    private final int stockCapacity;

    /**
     * Default constructor.
     *
     * @param chefsWorkEfficiency   list with ECE of chefs
     * @param couriersTrunkCapacity list with capacity of couriers trunks
     * @param stockCapacity         stock capacity
     */
    public PizzeriaSystem(@JsonProperty("chefsWorkEfficiency") List<Integer> chefsWorkEfficiency,
                          @JsonProperty(
                                  "couriersTrunkCapacity") List<Integer> couriersTrunkCapacity,
                          @JsonProperty("stockCapacity") int stockCapacity) {
        this.chefsWorkEfficiency = chefsWorkEfficiency;
        this.couriersTrunkCapacity = couriersTrunkCapacity;
        this.stockCapacity = stockCapacity;
    }
}
