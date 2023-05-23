package ru.nsu.valikov.dsl

import ru.nsu.valikov.models.Group

import static groovy.lang.Closure.DELEGATE_ONLY

class GroupSetupDsl {
    static void groups(@DelegatesTo(value = GroupsDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var groupsDsl = new GroupsDsl();
        closure.delegate = groupsDsl;
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class GroupsDsl {
        protected static final Map<Integer, Group> groupMap = new HashMap<>();

        static void group(List<String> studentList, @DelegatesTo(value = Group, strategy = DELEGATE_ONLY) Closure closure) {
            var group = new Group(studentList)
            closure.delegate = group
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
            groupMap.put(group.getId(), group)
            println groupMap
        }
    }
}
