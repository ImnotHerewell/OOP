package ru.nsu.valikov.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * Group with students.
 */
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group {

    public static final Map<Integer, Group> groups = new HashMap<>();
    @NonNull
    List<String> students;
    @NonNull
    Integer id;

    public Group(@NonNull List<String> studentList) {
        students = studentList;
    }
}
