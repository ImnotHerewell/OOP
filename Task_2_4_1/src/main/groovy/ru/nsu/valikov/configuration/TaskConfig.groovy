package ru.nsu.valikov.configuration

import static ru.nsu.valikov.dsl.TaskSetupDsl.tasks

tasks {
    task {
        id = 1
        name = "example"
        score = 20
    }
    task {
        id = 2
        name = "example2"
        score = 30
    }
    task {
        id = 3
        name = "example3"
        score = 50
    }
}