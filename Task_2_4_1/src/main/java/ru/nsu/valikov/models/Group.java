package ru.nsu.valikov.models;

import java.util.HashSet;
import java.util.List;
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
public class Group {

    static final HashSet<Integer> IDS = new HashSet<>();
    @NonNull
    List<String> students;
    @NonNull
    Integer id;

    public Group(List<String> studentList) {
        students = studentList;
    }
}
