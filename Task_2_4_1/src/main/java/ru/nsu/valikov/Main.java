package ru.nsu.valikov;

import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.valikov.service.GitService;
import ru.nsu.valikov.service.GradleService;
import ru.nsu.valikov.service.dto.BuildProjectRequest;
import ru.nsu.valikov.service.dto.CloneProjectRequest;

public class Main {

    public static void main(String[] args) throws GitAPIException {
        System.out.println(GitService.cloneProject(
            new CloneProjectRequest("https://github.com/nocarend/oop_workshop",
                "main", "nocarend")));
        System.out.println(GradleService.buildProject(new BuildProjectRequest("nocarend/example")));
        Utils.deleteDirectory(Configuration.DEFAULT_TASKS_DIRECTORY);
    }
}
