package ru.nsu.valikov;

public record Semester(int semesterNumber) implements Comparable<Semester> {
    @Override
    public int compareTo(Semester anotherSemester) {
        return semesterNumber - anotherSemester.semesterNumber;
    }
}
