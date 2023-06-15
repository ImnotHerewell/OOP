package ru.nsu.valikov.dsl.vlasov

import ru.nsu.valikov.dsl.LessonSetupDsl
import ru.nsu.valikov.dsl.StudentSetupDsl
import ru.nsu.valikov.dsl.vlasov.entites.AttendanceStudentIds
import ru.nsu.valikov.models.Student

import java.time.LocalDate

import static groovy.lang.Closure.DELEGATE_ONLY

class AttendOptionDsl {
    static void attendance(@DelegatesTo(value = AttendancesDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var attendanceDsl = new AttendancesDsl()
        closure.delegate = attendanceDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class AttendancesDsl {
        static var attendanceMap = Student.attendanceMap

        static void day(String date, @DelegatesTo(value = AttendanceStudentIds, strategy = DELEGATE_ONLY) Closure closure) {
            var students = new AttendanceStudentIds()
            closure.delegate = students
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
            var parsedDate = LocalDate.parse(date)
            if (!LessonSetupDsl.datesSet.contains(parsedDate)) {
                throw new NoSuchElementException("No lesson at given date.")
            }
            for (String nickname : students.getStudentNickNames()) {
                if (!StudentSetupDsl.studentMap.containsKey(nickname)) {
                    throw new NoSuchElementException("No student with given nickname.")
                }
                if (!attendanceMap.keySet().contains(nickname)) {
                    attendanceMap.put(nickname, new TreeSet<>())
                }
                attendanceMap.get(nickname).add(parsedDate);
            }
            println attendanceMap
        }
    }
}
