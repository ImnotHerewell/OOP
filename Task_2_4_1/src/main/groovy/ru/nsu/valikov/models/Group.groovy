package ru.nsu.valikov.models

class Group {
    int id // в разные годы группы с одинаковым именем должны быть разными
    String name
    List<GroupTask> tasks
}
