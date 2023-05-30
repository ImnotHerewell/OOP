package ru.nsu.valikov.models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssuedTask {

    public static final Map<Integer, IssuedTask> taskMap = new HashMap<>();
    @NonNull
    Integer taskId;
    @NonNull
    LocalDate deadline;
    @NonNull
    List<String> students;

    public IssuedTask(@NonNull String stringDate) {
        deadline = LocalDate.parse(stringDate);
    }
}
