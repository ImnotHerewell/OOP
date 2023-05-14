package ru.nsu.valikov.models;

import java.util.HashSet;
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
public class Task {

    static final HashSet<String> NAMES = new HashSet<>();
    @NonNull
    String name;
    @NonNull
    Integer score;
}
