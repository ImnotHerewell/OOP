package ru.nsu.valikov;

public record Semester(int semesterNumber) {
    public boolean compareTo(int semesterNumber1, int semesterNumber2) {
        return semesterNumber1 > semesterNumber2;
    }
}
