package ru.nsu.valikov.service.generator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity for group score markdown.
 */
@Data
@AllArgsConstructor
public class GroupScoreGeneratorEntity {

    private final String name;
    private final Double totalScore;
}
