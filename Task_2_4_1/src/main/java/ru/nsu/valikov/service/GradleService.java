package ru.nsu.valikov.service;

import static ru.nsu.valikov.Configuration.DEFAULT_TASKS_DIRECTORY;
import static ru.nsu.valikov.service.ServiceUtils.SLASH;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import ru.nsu.valikov.service.dto.BuildProjectRequest;
import ru.nsu.valikov.service.dto.CheckStyleRequest;
import ru.nsu.valikov.service.dto.GenerateDocumentationRequest;

@UtilityClass
@Log
public class GradleService {

    private static final String COMMAND_JAVADOC = "javadoc";

    private static final String COMMAND_BUILD = "build";
    private static final String COMMAND_CHECKSTYLE = "checkstyle";
    private static final String BUILD_GRADLE = "build.gradle";

    private static ProjectConnection openGradleConnection(String projectPath) {
        return GradleConnector.newConnector()
            .forProjectDirectory(new File(DEFAULT_TASKS_DIRECTORY + SLASH + projectPath)).connect();
    }

    private static void execute(@NonNull String project, @NonNull String command) {
        @Cleanup val connection = openGradleConnection(project);
        connection.newBuild().forTasks(command).setStandardOutput(System.out).run();
    }

    public boolean buildProject(@NonNull BuildProjectRequest buildProjectRequest) {
        val project = buildProjectRequest.project();
        try {
            execute(project, COMMAND_BUILD);
            log.info(project + " has successfully built");
            return true;
        } catch (GradleConnectionException | IllegalStateException e) {
            log.warning(project + " has not built for unexpected reasons");
            return false;
        }
    }

    public boolean generateDocumentation(
        @NonNull GenerateDocumentationRequest generateDocumentationRequest) {
        val project = generateDocumentationRequest.project();
        try {
            execute(project, COMMAND_JAVADOC);
            log.info(project + " has successfully generated documentation");
            return true;
        } catch (GradleConnectionException | IllegalStateException e) {
            log.warning(project + " has not generated documentation for unexpected reasons");
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
    public boolean checkStyle(@NonNull CheckStyleRequest checkStyleRequest) {
        val project = checkStyleRequest.project();
        @Cleanup val connection = openGradleConnection(project);
        if (!addCheckstylePluginToProjectGradle(project) || !copyCheckstyleConfigToProject(
            project)) {
            return false;
        }
        try {
            connection.newBuild().forTasks("checkstyleMain").setStandardOutput(System.out).run();
            log.info(project + " has successfully passed check style test");
            return true;
        } catch (GradleConnectionException | IllegalStateException e) {
            e.printStackTrace();
            log.warning(project + " has not passed check style test");
            return false;
        }
    }

    @SneakyThrows
    private static boolean addCheckstylePluginToProjectGradle(@NonNull String project) {
        final List<String> CHECKSTYLE_PLUGIN = new ArrayList<>(
            List.of("plugins\n{\nid 'checkstyle'\n}"));
        val file = new File(DEFAULT_TASKS_DIRECTORY + SLASH + project + SLASH + BUILD_GRADLE);
        CHECKSTYLE_PLUGIN.addAll(FileUtils.readLines(file));
        FileUtils.writeLines(file, CHECKSTYLE_PLUGIN);
        return true;
    }

    @SneakyThrows
    private static boolean copyCheckstyleConfigToProject(@NonNull String project) {
        val path = "config/checkstyle/checkstyle.xml";
        val source = new File(path);
        val destination = new File(DEFAULT_TASKS_DIRECTORY + SLASH + project + SLASH + path);
        if (destination.exists()) {
            log.warning(project + " already contains checkstyle.xml");
            return false;
//            throw new FileAlreadyExistsException(
//                "WTF?! Why is your checkstyle.xml already exists??");
        }
        FileUtils.copyFile(source, destination);
        return true;
    }
}
