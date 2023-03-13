package ru.nsu.valikov.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Just for reading from JSON.
 *
 * @param chefsWorkEfficiency   list with ECE of chefs
 * @param couriersTrunkCapacity list with capacity of couriers trunks
 * @param stockCapacity         stock capacity
 */
public record PizzeriaSystem(@JsonProperty("chefsWorkEfficiency") List<Integer> chefsWorkEfficiency,
        @JsonProperty("couriersTrunkCapacity") List<Integer> couriersTrunkCapacity,
        @JsonProperty int stockCapacity) {
}
