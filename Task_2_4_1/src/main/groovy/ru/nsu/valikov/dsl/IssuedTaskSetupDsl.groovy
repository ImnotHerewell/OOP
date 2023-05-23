package ru.nsu.valikov.dsl

import ru.nsu.valikov.models.IssuedTask

import static groovy.lang.Closure.DELEGATE_ONLY

class IssuedTaskSetupDsl {
    static void issuedTasks(@DelegatesTo(value = IssuedTasksDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var issuedTasksDsl = new IssuedTasksDsl()
        closure.delegate = issuedTasksDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class IssuedTasksDsl {
        protected static final Map<Integer, IssuedTask> issuedTaskMap=new HashMap<>()
        static void issuedTask(String date, @DelegatesTo(value = IssuedTask, strategy = DELEGATE_ONLY) Closure closure){
            var issuedTask=new IssuedTask(date)
            closure.delegate=issuedTask
            closure.resolveStrategy=DELEGATE_ONLY
            closure.call()
            issuedTaskMap.put(issuedTask.getId(),issuedTask)
            println issuedTaskMap
        }
    }
}
