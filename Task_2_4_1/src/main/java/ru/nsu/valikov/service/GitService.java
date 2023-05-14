package ru.nsu.valikov.service;

import static ru.nsu.valikov.Configuration.processBuilder;

import java.io.File;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.valikov.Configuration;
import ru.nsu.valikov.service.dto.BuildProjectRequest;
import ru.nsu.valikov.service.dto.CloneProjectRequest;

@UtilityClass
public class GitService {

    public boolean cloneProject(CloneProjectRequest cloneProjectRequest) throws GitAPIException {
        @Cleanup
        val git = Git.cloneRepository()
            .setURI(cloneProjectRequest.uri() + ".git")
            .setBranch(cloneProjectRequest.branch())
            .setDirectory(new File(Configuration.DEFAULT_TASKS_DIRECTORY.toString(),
                cloneProjectRequest.student()))
            .call();
        return true;
    }

}
