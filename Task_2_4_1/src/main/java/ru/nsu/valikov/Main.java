package ru.nsu.valikov;

import java.io.File;
import lombok.val;
import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.valikov.models.Group;
import ru.nsu.valikov.models.IssuedTask;
import ru.nsu.valikov.models.Student;
import ru.nsu.valikov.models.Task;
import ru.nsu.valikov.service.GitService;
import ru.nsu.valikov.service.GradleService;
import ru.nsu.valikov.service.GroovyExecutor;
import ru.nsu.valikov.service.dto.CheckTestsRequest;
import ru.nsu.valikov.service.dto.CloneProjectRequest;
import ru.nsu.valikov.service.dto.CompileProjectRequest;
import ru.nsu.valikov.service.dto.GenerateDocumentationRequest;
import ru.nsu.valikov.service.generator.ReportGeneratorService;

public class Main {

    public static void main(String[] args) throws GitAPIException {
        try {
            GroovyExecutor.execute("FullConfig.groovy");
            val students = Student.studentMap;
            var tasks = IssuedTask.taskMap;
            for (val task : tasks.values()) {
                val taskStudents = task.getStudents();
                for (val student : taskStudents) {
                    val info = students.get(student);
                    val isCloned = GitService.cloneProject(
                        new CloneProjectRequest(info.getRepositoryUrl(), info.getBranchName(),
                            info.getNickName()));
                    val projectName =
                        info.getNickName() + "/" + Task.taskMap.get(task.getTaskId()).getName();
                    val isCompiled = GradleService.compileProject(new CompileProjectRequest(
                        projectName));
                    val isDocumented = GradleService.generateDocumentation(
                        new GenerateDocumentationRequest(projectName));
                    val testValue = GradleService.getCoefficientOfPassedTests(
                        new CheckTestsRequest(projectName));
//                    val checkStyleValue = GradleService.getCoefficientOfCheckStyle(
//                        new CheckTestsRequest(projectName));
                    Utils.deleteDirectory(
                        new File(Configuration.DEFAULT_TASKS_DIRECTORY + "/" + info.getNickName()));
                }
            }
            Group.groups.keySet().forEach(ReportGeneratorService::generateAttendance);
        } finally {
            Utils.deleteDirectory(Configuration.DEFAULT_TASKS_DIRECTORY);
        }
    }
}
