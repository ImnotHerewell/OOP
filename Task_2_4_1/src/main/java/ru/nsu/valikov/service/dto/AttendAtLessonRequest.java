package ru.nsu.valikov.service.dto;

import java.time.LocalDate;
import lombok.NonNull;

/**
 * haha.
 *
 * @param project name
 * @param lesson  date
 */
public record AttendAtLessonRequest(@NonNull String project, @NonNull LocalDate lesson) {

}
