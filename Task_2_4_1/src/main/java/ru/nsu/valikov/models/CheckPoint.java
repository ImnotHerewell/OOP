package ru.nsu.valikov.models;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * Useless entity, but let it be.
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckPoint {

    @NonNull
    String name;
    @NonNull
    LocalDate date;

    public CheckPoint(@NonNull String stringDate) {
        this.date = LocalDate.parse(stringDate);
    }

}
