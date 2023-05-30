package config

import static ru.nsu.valikov.dsl.CheckPointSetupDsl.checkpoints
import static ru.nsu.valikov.dsl.GroupSetupDsl.groups
import static ru.nsu.valikov.dsl.IssuedTaskSetupDsl.issuedTasks
import static ru.nsu.valikov.dsl.LessonSetupDsl.lessons
import static ru.nsu.valikov.dsl.StudentSetupDsl.students
import static ru.nsu.valikov.dsl.TaskSetupDsl.tasks
import static ru.nsu.valikov.dsl.vlasov.AttendOptionDsl.attendance
import static ru.nsu.valikov.dsl.vlasov.CompleteTaskOptionDsl.completed

students {
    student {
        nickName = "deril"
        realName = "who cares"
        repositoryUrl = "https://github.com/nocarend/OOP"
    }
    student {
        nickName = "martin"
        realName = "who cares"
        repositoryUrl = "https://github.com/nocarend/oop_workshop"
        branchName = "main"
    }
    student {
        nickName = "aboba"
        realName = "who cares"
        repositoryUrl = "https://github.com/nocarend/OOP"
    }
}

groups {
    group(["deril", "martin"]) {
        id = 21215
    }
    group(["aboba"]) {
        id = 21216
    }
}

tasks {
    task {
        id = 1
        name = "example"
        score = 20
    }
    task {
        id = 2
        name = "Task_1_2_1"
        score = 30
    }
    task {
        id = 3
        name = "Task_1_1_1"
        score = 50
    }
}
issuedTasks {
    issuedTask("2023-09-28") {
        taskId = 1
        students = ["martin"]
    }
    issuedTask("2023-05-30") {
        taskId = 2
        students = ["aboba", "deril"]
    }
    issuedTask("2023-05-30") {
        taskId = 3
        students = ["aboba"]
    }
}

completed {
    task(1, 0.7, "2023-08-27") {
        studentNickNames = ["martin"]
        description = "Stupid morons"
    }
    task(2, 2, "2023-04-27") {
        studentNickNames = ["aboba", "deril"]
        description = "Good bad"
    }
    task(3, 1, "2023-05-27") {
        studentNickNames = ["aboba"]
        description = "Ugly"
    }
}

lessons {
    lesson("2023-06-23")
    lesson("2023-07-23")
}

attendance {
    day("2023-06-23") {
        studentNickNames = ["aboba", "deril"]
    }
}

checkpoints {
    checkpoint("2024-03-25") {
        name = "4fun"
    }
}
