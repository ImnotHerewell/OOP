package ru.nsu.valikov.models;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssuedTask {

    static int idCounter = 0;
    @Getter(lazy = true)
    final Integer id = idCounter++;
    @NonNull
    Integer taskId;
    @NonNull
    LocalDate deadline;

    public IssuedTask(@NonNull String stringDate) {
        deadline = LocalDate.parse(stringDate);
    }
}
