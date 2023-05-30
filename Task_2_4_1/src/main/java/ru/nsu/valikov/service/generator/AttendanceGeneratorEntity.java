package ru.nsu.valikov.service.generator;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.mjaron.etudes.Str;
@Data
@AllArgsConstructor
public class AttendanceGeneratorEntity {
    private final List<LocalDate> dates;
    private final String name;
}
