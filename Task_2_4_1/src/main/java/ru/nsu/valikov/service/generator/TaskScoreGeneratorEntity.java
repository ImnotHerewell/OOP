package ru.nsu.valikov.service.generator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskScoreGeneratorEntity {

    private final String studentName;
    private final Boolean compile;
    private final Boolean checkstyle;
    private final Boolean javadoc;
    private final Double passedTestsCoefficient;
    private final Double score;
}
