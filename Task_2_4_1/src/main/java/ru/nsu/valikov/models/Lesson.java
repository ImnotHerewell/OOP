package ru.nsu.valikov.models;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson implements Comparable<Lesson> {

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
