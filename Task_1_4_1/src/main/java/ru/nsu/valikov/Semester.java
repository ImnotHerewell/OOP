package ru.nsu.valikov;

/**
 * Record like wrapper for semester int.
 *
 * @param semesterNumber int, which represents semester's number (lol)
 */
public record Semester(int semesterNumber) implements Comparable<Semester> {
    @Override
    public int compareTo(Semester anotherSemester) {
        return semesterNumber - anotherSemester.semesterNumber;
    }
}
