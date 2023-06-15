package ru.nsu.valikov.models;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * Day when teaches comes.
 */
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson implements Comparable<Lesson> {

    public static final Set<LocalDate> datesSet = new TreeSet<>();

    @NonNull
    LocalDate date;

    public Lesson(@NonNull String stringDate) {
        date = LocalDate.parse(stringDate);
    }

    @Override
    public int compareTo(Lesson o) {
        return date.compareTo(o.date);
    }
}
