package ru.nsu.valikov.service;

import static ru.nsu.valikov.Configuration.DEFAULT_TASKS_DIRECTORY;
import static ru.nsu.valikov.service.ServiceUtils.BUILD;
import static ru.nsu.valikov.service.ServiceUtils.SLASH;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilderFactory;
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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.nsu.valikov.service.dto.CheckTestsRequest;
import ru.nsu.valikov.service.dto.CompileProjectRequest;
import ru.nsu.valikov.service.dto.GenerateDocumentationRequest;

/**
 * Service class that handles all gradle features.
 */
@UtilityClass
@Log
public class GradleService {

    private static final String COMMAND_JAVADOC = "javadoc";

    private static final String COMMAND_COMPILE = "compileJava";
    private static final String COMMAND_CHECKSTYLE = "checkstyleMain";
    private static final String COMMAND_TEST = "test";
    private static final String BUILD_GRADLE = "build.gradle";
    private static final String TEST_RESULTS = "test-results";
    private static final String TEST = "test";
    private static final String CHECKSTSYLE = "checkstyle";
    private static final String REPORTS = "reports";

    private static ProjectConnection openGradleConnection(String projectPath) {
        return GradleConnector.newConnector().useGradleVersion("8.0")
            .forProjectDirectory(new File(DEFAULT_TASKS_DIRECTORY + SLASH + projectPath)).connect();
    }

    private static void execute(@NonNull String project, @NonNull String command) {
        @Cleanup val connection = openGradleConnection(project);
        connection.newBuild().forTasks(command).setStandardOutput(System.out).run();
    }

    public boolean compileProject(@NonNull CompileProjectRequest compileProjectRequest) {
        val project = compileProjectRequest.project();
        try {
            execute(project, COMMAND_COMPILE);
            log.info(project + " has successfully compiled");
            return true;
        } catch (GradleConnectionException | IllegalStateException e) {
            log.warning(project + " has not compiled for unexpected reasons");
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

    /**
     * Get ration of successfully passed tests.
     *
     * @param checkTestsRequest dto containing project that we want to check
     * @return ratio between passed and all tests
     */
    @SneakyThrows
    public static double getCoefficientOfPassedTests(@NonNull CheckTestsRequest checkTestsRequest) {
        if (executeTests(checkTestsRequest)) {
            return 1;
        }
        return coefficientOfPassedTests(checkTestsRequest);
    }

    private boolean executeTests(@NonNull CheckTestsRequest checkTestsRequest) {
        val project = checkTestsRequest.project();
        try {
            execute(project, COMMAND_TEST);
            log.info(project + " has successfully passed all tests");
            return true;
        } catch (GradleConnectionException | IllegalStateException e) {
            log.warning(project + " has not successfully passed all tests for unexpected reasons");
            return false;
        }
    }


    /**
     * Methods name displays all necessary information.
     *
     * @param checkTestsRequest dto containing project that we want to check
     * @return number of problems in code style checking
     */
    public int getNumberOfCheckStyleProblems(@NonNull CheckTestsRequest checkTestsRequest) {
        val project = checkTestsRequest.project();
        if (!addCheckstylePluginToProjectGradle(project) || !copyCheckstyleConfigToProject(
            project) || !executeCheckStyle(checkTestsRequest)) {
            return 0;
        }
        return numberOfBadReports(checkTestsRequest);
    }

    @SneakyThrows
    private int numberOfBadReports(@NonNull CheckTestsRequest checkTestsRequest) {
        val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        val reportsDirectory = new File(
            DEFAULT_TASKS_DIRECTORY + SLASH + checkTestsRequest.project() + SLASH + BUILD + SLASH
                + REPORTS + SLASH + CHECKSTSYLE);
        if (!reportsDirectory.exists()) {
            return 0;
        }
        var badReports = 0;
        for (val file : Objects.requireNonNull(
            reportsDirectory.listFiles())) { //проще самому парсер высрать
            try {
                val document = builder.parse(file);
                NodeList errorNodes = document.getElementsByTagName("error");
                badReports += errorNodes.getLength();
            } catch (IOException | SAXException e) {
                log.warning(file.getName() + " has not been read");
            }
        }
        return badReports;
    }

    private boolean executeCheckStyle(@NonNull CheckTestsRequest checkTestsRequest) {
        val project = checkTestsRequest.project();
        @Cleanup val connection = openGradleConnection(project);
        try {
            connection.newBuild().forTasks(COMMAND_CHECKSTYLE).setStandardOutput(System.out).run();
            log.info(project + " has successfully passed check style test");
            return true;
        } catch (GradleConnectionException | IllegalStateException e) {
            e.printStackTrace();
            log.warning(project + " has not passed check style test");
            return false;
        }
    }

    @SneakyThrows
    private boolean addCheckstylePluginToProjectGradle(@NonNull String project) {
        val checkstylePlugin = new ArrayList<>(
            List.of("plugins\n{\nid 'checkstyle'\n}"));
        val file = new File(DEFAULT_TASKS_DIRECTORY + SLASH + project + SLASH + BUILD_GRADLE);
        checkstylePlugin.addAll(FileUtils.readLines(file, StandardCharsets.UTF_8));
        FileUtils.writeLines(file, checkstylePlugin);
        return true;
    }

    @SneakyThrows
    private boolean copyCheckstyleConfigToProject(@NonNull String project) {
        val path = "config/checkstyle/checkstyle.xml";
        val source = new File(path);
        val destination = new File(DEFAULT_TASKS_DIRECTORY + SLASH + project + SLASH + path);
        if (destination.exists()) {
            val isSuccessfully = destination.delete();
            log.warning(project + " already contains checkstyle.xml");
            log.info(isSuccessfully ? "old checkstyle was deleted"
                : "unexpected error during deletion.");
        }
        FileUtils.copyFile(source, destination);
        return true;
    }

    @SneakyThrows
    private double coefficientOfPassedTests(@NonNull CheckTestsRequest checkTestsRequest) {
        val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        val testsDirectory = new File(
            DEFAULT_TASKS_DIRECTORY + SLASH + checkTestsRequest.project() + SLASH + BUILD + SLASH
                + TEST_RESULTS + SLASH + TEST);
        if (!testsDirectory.exists()) {
            return 0;
        }
        var testsCount = 0.;
        var failedTests = 0.;
        for (val file : Objects.requireNonNull(testsDirectory.listFiles())) {
            try {
                val document = builder.parse(file);
                document.getDocumentElement().normalize();
                val element = (Element) document.getElementsByTagName("testsuite").item(0);
                testsCount += Integer.parseInt(element.getAttribute("tests"));
                failedTests += Integer.parseInt(element.getAttribute("skipped")) + Integer.parseInt(
                    element.getAttribute("failures")) + Integer.parseInt(
                    element.getAttribute("errors"));
            } catch (IOException | SAXException e) {
                log.warning(file.getName() + " has not been read");
            }
        }
        return testsCount == 0 ? 1 : 1 - failedTests / testsCount;
    }
}
