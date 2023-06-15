package ru.nsu.valikov.service;

import java.io.File;
import java.sql.Date;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import lombok.val;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import ru.nsu.valikov.Configuration;
import ru.nsu.valikov.service.dto.AttendAtLessonRequest;
import ru.nsu.valikov.service.dto.CloneProjectRequest;

@UtilityClass
@Log
public class GitService {

    private static final String COMMA_GIT = ".git";

    public boolean isClonedProject(@NonNull CloneProjectRequest cloneProjectRequest)
        throws GitAPIException {
        val student = cloneProjectRequest.student();
        val branch = cloneProjectRequest.branch();
        @Cleanup
        val git = Git.cloneRepository()
            .setURI(cloneProjectRequest.uri() + COMMA_GIT)
            .setBranch(branch)
            .setDirectory(new File(Configuration.DEFAULT_TASKS_DIRECTORY.toString(),
                student + "-" + branch + "-" + cloneProjectRequest.task()))
            .call();
        log.info(student + " repository has successfully cloned");
        return true;
    }

    @SneakyThrows
    public boolean isAttended(
        @NonNull AttendAtLessonRequest attendAtLessonRequest) { // только Бог знает зачем этот
        @Cleanup
        val git = Git.open(new File(attendAtLessonRequest.project()));
        var between = CommitTimeRevFilter.between(
            Date.valueOf(attendAtLessonRequest.lesson().minusDays(7)),
            Date.valueOf(attendAtLessonRequest.lesson()));
        var revCommit = git.log().setRevFilter(between).call();
        return revCommit.iterator().hasNext();
    }

}
