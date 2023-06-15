package ru.nsu.valikov.dsl

import ru.nsu.valikov.models.IssuedTask

import static groovy.lang.Closure.DELEGATE_ONLY

class IssuedTaskSetupDsl {
    public static final var taskMap = IssuedTask.taskMap


    static void issuedTasks(@DelegatesTo(value = IssuedTasksDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var issuedTasksDsl = new IssuedTasksDsl()
        closure.delegate = issuedTasksDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class IssuedTasksDsl {
        static void issuedTask(String date, @DelegatesTo(value = IssuedTask, strategy = DELEGATE_ONLY) Closure closure) {
            var issuedTask = new IssuedTask(date)
            closure.delegate = issuedTask
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
            if (!TaskSetupDsl.taskMap.containsKey(issuedTask.getTaskId())) {
                throw new NoSuchElementException("No task with given id.")
            }
            taskMap.put(issuedTask.getTaskId(), issuedTask)
            println taskMap
        }
    }
}
