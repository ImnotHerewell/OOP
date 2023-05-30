package ru.nsu.valikov.dsl


import ru.nsu.valikov.models.Lesson

import java.time.LocalDate

import static groovy.lang.Closure.DELEGATE_ONLY

class LessonSetupDsl {
    public static final Set<LocalDate> datesSet = new TreeSet<>()

    static void lessons(@DelegatesTo(value = LessonsDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var lessonsDsl = new LessonsDsl()
        closure.delegate = lessonsDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class LessonsDsl {

        static void lesson(String date) {
            var lesson = new Lesson(date)
            datesSet.add(lesson.date)
            println datesSet
        }
    }
}
