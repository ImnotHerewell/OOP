package ru.nsu.valikov.service;

import static ru.nsu.valikov.Configuration.DEFAULT_TASKS_DIRECTORY;
import static ru.nsu.valikov.service.ServiceUtils.COMMAND_BUILD;
import static ru.nsu.valikov.service.ServiceUtils.SLASH;

import java.io.File;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.gradle.tooling.BuildAction;
import org.gradle.tooling.BuildActionExecuter;
import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import ru.nsu.valikov.service.dto.BuildProjectRequest;
import ru.nsu.valikov.service.dto.CheckTestsRequest;
import ru.nsu.valikov.service.dto.GenerateDocumentationRequest;

@UtilityClass
public class GradleService {

    private static final String COMMAND_JAVADOC = "javadoc";

    private static ProjectConnection openGradleConnection(String projectPath) {
        return GradleConnector.newConnector()
            .forProjectDirectory(new File(DEFAULT_TASKS_DIRECTORY + SLASH + projectPath)).connect();
    }

    public boolean buildProject(@NonNull BuildProjectRequest buildProjectRequest) {
        @Cleanup
        val connection = openGradleConnection(buildProjectRequest.project());
        try {
            connection.newBuild().forTasks(COMMAND_BUILD).setStandardOutput(System.out).run();
            return true;
        } catch (GradleConnectionException | IllegalStateException e) {
            return false;
        }
    }

    public boolean generateDocumentation(
        @NonNull GenerateDocumentationRequest generateDocumentationRequest) {
        @Cleanup
        val connection = openGradleConnection(generateDocumentationRequest.project());
        try {
            connection.newBuild().forTasks(COMMAND_JAVADOC).setStandardOutput(System.out).run();
            return true;
        } catch (GradleConnectionException | IllegalStateException e) {
            return false;
        }
    }

//    @SneakyThrows
//    public boolean checkTests(@NonNull CheckTestsRequest checkTestsRequest) {
//        @Cleanup
//        val connection = openGradleConnection(checkTestsRequest.project());
//        try {
//            connection.newTestLauncher().withJvmTestClasses()
//        }
//    }
}
