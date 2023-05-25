package ru.nsu.valikov;

import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.valikov.service.GitService;
import ru.nsu.valikov.service.GradleService;
import ru.nsu.valikov.service.dto.BuildProjectRequest;
import ru.nsu.valikov.service.dto.CheckStyleRequest;
import ru.nsu.valikov.service.dto.CloneProjectRequest;
import ru.nsu.valikov.service.dto.GenerateDocumentationRequest;

public class Main {

    public static void main(String[] args) throws GitAPIException {
        try {
            System.out.println(GitService.cloneProject(
                new CloneProjectRequest("https://github.com/nocarend/oop_workshop",
                    "main", "nocarend")));

//            System.out.println(
//                GradleService.buildProject(new BuildProjectRequest("nocarend/example")));
//            System.out.println(GradleService.generateDocumentation(
//                new GenerateDocumentationRequest("nocarend/example")));
            System.out.println(GradleService.checkStyle(
                new CheckStyleRequest("nocarend/example")));
        } finally {
//            Utils.deleteDirectory(Configuration.DEFAULT_TASKS_DIRECTORY);
        }
    }
}
