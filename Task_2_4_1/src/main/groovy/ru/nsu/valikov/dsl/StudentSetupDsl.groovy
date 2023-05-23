package ru.nsu.valikov.dsl


import ru.nsu.valikov.models.Student

import static groovy.lang.Closure.DELEGATE_ONLY

class StudentSetupDsl {
    static void students(@DelegatesTo(value = StudentsDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var studentsDsl = new StudentsDsl()
        closure.delegate = studentsDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class StudentsDsl {
        protected static final Map<String, Student> studentMap = new HashMap<>()

        static void student(@DelegatesTo(value = Student, strategy = DELEGATE_ONLY) Closure closure) {
            var student = new Student()
            closure.delegate = student
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
            studentMap.put(student.getNickName(), student)
            println studentMap
        }
    }
}
