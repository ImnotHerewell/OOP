package ru.nsu.valikov.dsl.vlasov.entites;

import static java.lang.Math.max;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import ru.nsu.valikov.models.IssuedTask;
import ru.nsu.valikov.models.Task;


/**
 * Real entity, that calculates score by info of completed task.
 */
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompletedTask {

    static final double DAY_PENALTY = 0.05;
    @NonNull
    final Integer id;
    @NonNull
    final Double coefficient;
    @NonNull
    final LocalDate date;

    /**
     * Calculates score of completed task.
     *
     * @return balls ну типа болз
     */
    public double toBalls() {
        var tasks = Task.taskMap;
        var issued = IssuedTask.taskMap;
        if (tasks.containsKey(id) && issued.containsKey(id)) {
            return tasks.get(id).getScore() * coefficient * max(0,
                1 - max(0, -DAYS.between(date, issued.get(id).getDeadline())) * DAY_PENALTY);
        }
        throw new NoSuchElementException("No task with such id");
    }

}
