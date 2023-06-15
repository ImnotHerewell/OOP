package ru.nsu.valikov.service.generator;


import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity for attendance markdown.
 */
@Data
@AllArgsConstructor
public class AttendanceGeneratorEntity {

    private final List<LocalDate> dates;
    private final String name;
}
