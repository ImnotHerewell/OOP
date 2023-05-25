package ru.nsu.valikov.service;

import java.io.File;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import lombok.val;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.valikov.Configuration;
import ru.nsu.valikov.service.dto.CloneProjectRequest;

@UtilityClass
@Log
public class GitService {

    private static final String COMMA_GIT = ".git";

    public boolean cloneProject(CloneProjectRequest cloneProjectRequest) throws GitAPIException {
        val student = cloneProjectRequest.student();
        @Cleanup
        val git = Git.cloneRepository()
            .setURI(cloneProjectRequest.uri() + COMMA_GIT)
            .setBranch(cloneProjectRequest.branch())
            .setDirectory(new File(Configuration.DEFAULT_TASKS_DIRECTORY.toString(),
                student))
            .call();
        log.info(student +" repository has successfully cloned");
        return true;
    }

}
