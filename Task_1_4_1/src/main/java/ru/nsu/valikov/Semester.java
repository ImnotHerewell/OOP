package ru.nsu.valikov;

/**
 * Record like wrapper for semester int.
 *
 * @param semesterNumber int, which represents semester's number (lol)
 */
public record Semester(int semesterNumber) implements Comparable<Semester> {

    public Semester(int semesterNumber) {
        this.semesterNumber = semesterNumber > 0 && semesterNumber < 13 ? semesterNumber : -1;
        if (this.semesterNumber == -1) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int compareTo(Semester anotherSemester) {
        return semesterNumber - anotherSemester.semesterNumber;
    }
}
