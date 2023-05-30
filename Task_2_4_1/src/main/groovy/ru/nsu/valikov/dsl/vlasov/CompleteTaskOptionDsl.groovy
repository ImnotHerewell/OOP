package ru.nsu.valikov.dsl.vlasov

import ru.nsu.valikov.dsl.StudentSetupDsl
import ru.nsu.valikov.dsl.TaskSetupDsl
import ru.nsu.valikov.dsl.vlasov.entites.AttendanceStudentIds
import ru.nsu.valikov.dsl.vlasov.entites.CompletedTask
import ru.nsu.valikov.dsl.vlasov.entites.CompletedTaskByStudentIds

import java.time.LocalDate

import static groovy.lang.Closure.DELEGATE_ONLY

class CompleteTaskOptionDsl {
    static void completed(@DelegatesTo(value = CompletedDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var completedDsl = new CompletedDsl()
        closure.delegate = completedDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class CompletedDsl {
        public static final Map<String, Map<Integer, Double>> studentBallsMap = new HashMap<>();

        static void task(Integer id, double coefficient, String date, @DelegatesTo(value = AttendanceStudentIds, strategy = DELEGATE_ONLY) Closure closure) {
            if (!TaskSetupDsl.taskMap.containsKey(id)) {
                throw new NoSuchElementException("No task with given id.")
            }
            var studentIds = new CompletedTaskByStudentIds()
            closure.delegate = studentIds
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
            var parsedDate = LocalDate.parse(date)
            var task = new CompletedTask(id, coefficient, parsedDate)
            var score = task.toBalls()
            for (String student : studentIds.getStudentNickNames()) {
                if (!StudentSetupDsl.studentMap.containsKey(student)) {
                    throw new NoSuchElementException("No student with given nickname.")
                }
                studentBallsMap.putIfAbsent(student, new HashMap<Integer, Double>())
                studentBallsMap.get(student).put(id, score)
            }
            println studentBallsMap
        }
    }
}
