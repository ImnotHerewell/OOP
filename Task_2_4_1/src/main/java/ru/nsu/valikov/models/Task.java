package ru.nsu.valikov.models;

import java.util.HashSet;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {

    static final HashSet<Integer> IDS = new HashSet<>();
    @NonNull
    Integer id;
    @NonNull
    String name;
    @NonNull
    Integer score;

}
