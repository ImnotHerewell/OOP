package ru.nsu.valikov.dsl

import ru.nsu.valikov.models.Group

import static groovy.lang.Closure.DELEGATE_ONLY

class GroupSetupDsl {
    public static final Map<Integer, Group> groupMap = Group.groups

    static void groups(@DelegatesTo(value = GroupsDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var groupsDsl = new GroupsDsl();
        closure.delegate = groupsDsl;
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class GroupsDsl {

        static void group(List<String> studentList, @DelegatesTo(value = Group, strategy = DELEGATE_ONLY) Closure closure) {
            studentList.each { String student ->
                if (!StudentSetupDsl.studentMap.containsKey(student)) {
                    throw new NoSuchElementException("No student with given nickname. " + student)
                }
            }
            var group = new Group(studentList)
            closure.delegate = group
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
            groupMap.put(group.getId(), group)
            println groupMap
        }
    }
}
