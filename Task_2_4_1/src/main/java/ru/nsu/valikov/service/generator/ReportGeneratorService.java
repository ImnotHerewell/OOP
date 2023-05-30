package ru.nsu.valikov.service.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import lombok.val;
import pl.mjaron.etudes.Table;
import pl.mjaron.etudes.table.VerticalAlign;
import ru.nsu.valikov.models.Group;
import ru.nsu.valikov.models.Student;

@Log
@UtilityClass
public class ReportGeneratorService {

    public void generateAttendance(@NonNull Integer groupId) {
        List<AttendanceGeneratorEntity> mappings = new ArrayList<>();
        val groups = Group.groups;
        if (!groups.containsKey(groupId)) {
            throw new NoSuchElementException("No group with such id.");
        }
        for (String student : groups.get(groupId).getStudents()) {
            if (!Student.attendanceMap.containsKey(student)) {
                continue;
            }
            AttendanceGeneratorEntity attendanceGeneratorEntity = new AttendanceGeneratorEntity(
                Student.attendanceMap.get(student).stream().toList(),
                student);
            mappings.add(attendanceGeneratorEntity);
        }
        Table.render(mappings.toArray(new AttendanceGeneratorEntity[0]),
                AttendanceGeneratorEntity.class).html().withAlign(VerticalAlign.Right)
            .withAlign(1, VerticalAlign.Left)
            .toFile("build/" + groupId + ".html")
            .run();
        log.info("Attendance report has been successfully generated");
    }
}
