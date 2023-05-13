package ru.nsu.valikov.models;

import java.time.LocalDateTime;
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
public class GivenTask {

    static int idCounter = 0;
    @Getter(lazy = true)
    final Integer taskId = idCounter++;
    @NonNull
    LocalDateTime deadline;
}
