package ru.nsu.valikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SemesterTest {
    Semester semesterTest;

    @BeforeEach
    void initialization() {
        semesterTest = new Semester(7);
    }

    @Test
    void compareTo() {
        Semester anotherSemester = new Semester(1);
        Assertions.assertTrue(semesterTest.compareTo(anotherSemester) > 0);
        anotherSemester = new Semester(7);
        Assertions.assertEquals(0, semesterTest.compareTo(anotherSemester));
        anotherSemester = new Semester(8);
        Assertions.assertTrue(semesterTest.compareTo(anotherSemester) < 0);
    }

    @Test
    void semesterNumber() {
        Assertions.assertEquals(semesterTest.semesterNumber(), 7);
    }
}