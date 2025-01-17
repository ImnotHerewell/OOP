package ru.nsu.valikov.dsl


import ru.nsu.valikov.models.Task

import static groovy.lang.Closure.DELEGATE_ONLY

class TaskSetupDsl {
    public static final var taskMap = Task.taskMap

    static void tasks(@DelegatesTo(value = TasksDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var taskDsl = new TasksDsl()
        closure.delegate = taskDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class TasksDsl {

        static void task(@DelegatesTo(value = Task, strategy = DELEGATE_ONLY) Closure closure) {
            var task = new Task()
            closure.delegate = task
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
            taskMap.put(task.getId(), task)
            println taskMap
        }
    }
}
