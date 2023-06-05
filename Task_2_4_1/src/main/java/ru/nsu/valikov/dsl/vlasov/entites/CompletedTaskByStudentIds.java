package ru.nsu.valikov.dsl.vlasov.entites;

import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * Vlasovs dto, just watch classname.
 */
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompletedTaskByStudentIds {

    @NonNull
    List<String> studentNickNames;
    @NonNull
    String description;
}
