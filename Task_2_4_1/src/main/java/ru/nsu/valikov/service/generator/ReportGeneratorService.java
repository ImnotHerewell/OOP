package ru.nsu.valikov.service.generator;

import static ru.nsu.valikov.Configuration.DEFAULT_TASKS_DIRECTORY;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import lombok.val;
import pl.mjaron.etudes.Table;
import pl.mjaron.etudes.table.VerticalAlign;
import ru.nsu.valikov.models.Group;
import ru.nsu.valikov.models.IssuedTask;
import ru.nsu.valikov.models.Lesson;
import ru.nsu.valikov.models.Student;
import ru.nsu.valikov.models.Task;
import ru.nsu.valikov.service.GitService;
import ru.nsu.valikov.service.GradleService;
import ru.nsu.valikov.service.dto.AttendAtLessonRequest;
import ru.nsu.valikov.service.dto.CheckTestsRequest;
import ru.nsu.valikov.service.dto.CloneProjectRequest;
import ru.nsu.valikov.service.dto.CompileProjectRequest;
import ru.nsu.valikov.service.dto.GenerateDocumentationRequest;

@Log
@UtilityClass
public class ReportGeneratorService {

    public void generateGroupAttendance(@NonNull Integer groupId) {
        List<AttendanceGeneratorEntity> mappings = new ArrayList<>();
        val groups = Group.groups;
        if (!groups.containsKey(groupId)) {
            throw new NoSuchElementException("No group with such id.");
        }
        for (String student : groups.get(groupId).getStudents()) {
            val attendance = new HashSet<>(Student.attendanceMap.get(student));
            val realStudent = Student.studentMap.get(student);
            for (LocalDate lesson : Lesson.datesSet) {
                if (attendance.contains(lesson.toString())) {
                    continue;
                }
                if (GitService.isAttended(new AttendAtLessonRequest(
                    DEFAULT_TASKS_DIRECTORY + "/" + realStudent.getNickName() + "-"
                        + realStudent.getBranchName() + "-" + Task.taskMap.get(
                        IssuedTask.studentBallsMap.get(student).keySet().toArray()[0]).getName(),
                    lesson))) {
                    attendance.add(lesson);
                }
            }
            AttendanceGeneratorEntity attendanceGeneratorEntity = new AttendanceGeneratorEntity(
                attendance.stream().toList(), student);
            mappings.add(attendanceGeneratorEntity);
        }
        Table.render(mappings.toArray(new AttendanceGeneratorEntity[0]),
                AttendanceGeneratorEntity.class).markdown().withAlign(VerticalAlign.Right)
            .withAlign(1, VerticalAlign.Left).toFile("build/" + groupId + "-attendance.md").run();
        log.info("Attendance report has been successfully generated");
    }

    public void generateGroupScore(@NonNull Integer groupId) {
        List<GroupScoreGeneratorEntity> mappings = new ArrayList<>();
        val groups = Group.groups;
        if (!groups.containsKey(groupId)) {
            throw new NoSuchElementException("No group with such id.");
        }
        for (String student : groups.get(groupId).getStudents()) {
            val totalScore = IssuedTask.studentBallsMap.get(student).values().stream()
                .mapToDouble(Double::doubleValue).sum();
            mappings.add(
                new GroupScoreGeneratorEntity(Student.studentMap.get(student).getRealName(),
                    totalScore));
        }
        Table.render(mappings.toArray(new GroupScoreGeneratorEntity[0]),
                GroupScoreGeneratorEntity.class).markdown().withAlign(VerticalAlign.Right)
            .withAlign(1, VerticalAlign.Left).toFile("build/" + groupId + "-score.md").run();
        log.info("Group score report has been successfully generated");
    }

    @SneakyThrows
    public void generateScore(@NonNull Integer taskId) {
        List<TaskScoreGeneratorEntity> mappings = new ArrayList<>();
        val issuedTasks = IssuedTask.taskMap;
        if (!issuedTasks.containsKey(taskId)) {
            throw new NoSuchElementException("No issued task with such id.");
        }
        for (String student : issuedTasks.get(taskId).getStudents()) {
            val realStudent = Student.studentMap.get(student);
            val taskName = Task.taskMap.get(taskId).getName();
            if (!GitService.isClonedProject(
                new CloneProjectRequest(realStudent.getRepositoryUrl(), realStudent.getBranchName(),
                    realStudent.getNickName(), taskName))) {
                continue;
            }
            val projectName =
                realStudent.getNickName() + "-" + realStudent.getBranchName() + "-" + taskName + "/"
                    + Task.taskMap.get(taskId).getName();
            val isCompiles = GradleService.compileProject(
                new CompileProjectRequest(projectName));
            val isCheckstyled =
                GradleService.getNumberOfCheckStyleProblems(new CheckTestsRequest(projectName))
                    == 0;
            val isJavadoced = GradleService.generateDocumentation(
                new GenerateDocumentationRequest(projectName));
            val passedTestsCoefficient = GradleService.getCoefficientOfPassedTests(
                new CheckTestsRequest(projectName));
            val score = IssuedTask.studentBallsMap.get(student).get(taskId);
            mappings.add(
                new TaskScoreGeneratorEntity(realStudent.getRealName(), isCompiles, isCheckstyled,
                    isJavadoced, passedTestsCoefficient, score));
        }
        Table.render(mappings.toArray(new TaskScoreGeneratorEntity[0]),
                TaskScoreGeneratorEntity.class).markdown().withAlign(VerticalAlign.Right)
            .withAlign(1, VerticalAlign.Left).toFile("build/" + taskId + "-score.md").run();
        log.info("Task score report has been successfully generated");
    }
}
