package ru.nsu.valikov.service;

import static ru.nsu.valikov.Configuration.processBuilder;

import java.io.File;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import ru.nsu.valikov.Configuration;
import ru.nsu.valikov.service.dto.BuildProjectRequest;

@UtilityClass
public class GradleService {

    @SneakyThrows
    public boolean buildProject(BuildProjectRequest buildProjectRequest) {
        processBuilder.command("cmd.exe", "/c", "cd",
            Configuration.DEFAULT_TASKS_DIRECTORY + "/" + buildProjectRequest.project(), "&",
            "gradlew.bat", "build");
        val process = processBuilder.start();
        process.waitFor();
        return new File(Configuration.DEFAULT_TASKS_DIRECTORY + "/" + buildProjectRequest.project()
            + "/build").exists();
    }
//    public boolean generateDocumentation(){
//
//    }
}
