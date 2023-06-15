package ru.nsu.valikov;

import java.util.concurrent.Callable;
import org.eclipse.jgit.api.errors.GitAPIException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import ru.nsu.valikov.models.Group;
import ru.nsu.valikov.models.IssuedTask;
import ru.nsu.valikov.service.GroovyExecutor;
import ru.nsu.valikov.service.generator.ReportGeneratorService;

@Command(name = "vlasov_lab", mixinStandardHelpOptions = true, description = "Task pass")
public class Main implements Callable<Integer> {

    @Option(names = {"-a",
        "--attentance"}, description = "Should we track and generate an attendance report?")
    private Boolean attendance = false;
    @Option(names = {"-s", "--score"}, description = "Should we generate a score report?")
    private Boolean scoreReport = false;
    @Option(names = {"-f", "--file"}, description = "Path to the/a config file.")
    private String filename;

    public static void main(String... args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws GitAPIException {
        try {
            GroovyExecutor.execute(filename);
            if (scoreReport) {
                IssuedTask.taskMap.keySet().forEach(ReportGeneratorService::generateScore);
                Group.groups.keySet().forEach(ReportGeneratorService::generateGroupScore);
            }
            if (attendance) {
                Group.groups.keySet().forEach(ReportGeneratorService::generateGroupAttendance);
            }
        } finally {
            Utils.deleteDirectory(Configuration.DEFAULT_TASKS_DIRECTORY);
        }
        return 0;
    }
}
