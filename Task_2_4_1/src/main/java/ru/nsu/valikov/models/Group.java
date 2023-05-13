package ru.nsu.valikov.models;

import java.util.HashSet;
import java.util.List;
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
public class Group {

    static final HashSet<Integer> IDS = new HashSet<>();
    @NonNull
    final List<Student> studentList;
    @NonNull
    Integer id;
}
