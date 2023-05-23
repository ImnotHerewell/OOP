package ru.nsu.valikov.dsl


import ru.nsu.valikov.models.Lesson

import static groovy.lang.Closure.DELEGATE_ONLY

class LessonSetupDsl {
    static void lessons(@DelegatesTo(value = LessonsDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var lessonsDsl = new LessonsDsl()
        closure.delegate = lessonsDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class LessonsDsl {
        protected static final Set<Lesson> lessonSet = new TreeSet<>()

        static void lesson(String date) {
            var lesson = new Lesson(date)
            lessonSet.add(lesson)
            println lessonSet
        }
    }
}
