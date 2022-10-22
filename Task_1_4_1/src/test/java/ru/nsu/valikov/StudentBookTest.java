package ru.nsu.valikov;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentBookTest {
    private static final double EPS = 0.0000000001;
    private StudentBook testBook;

    @BeforeEach
    void init() {
        Map<String, Mark> firstSemesterGrades = new HashMap<>() {
            {
                put("Introduction to Algebra and Analysis", Mark.C.setRetakeCount(1));
                put("Introduction to Discrete Mathematics and Mathematical Logic", Mark.C);
                put("Declarative Programming", Mark.A);
                put("Imperative Programming", Mark.A);
                put("English", Mark.Z);
                put("History", Mark.A);
                put("Basics of speech culture", Mark.A);
                put("Physical Culture and sport", Mark.Z);
                put("Physical Culture and sports (elective discipline)", Mark.Z);
                put("Digital platforms", Mark.Z);
            }
        };
        Map<String, Mark> secondSemesterGrades = new HashMap<>() {
            {
                put("Introduction to Algebra and Analysis", Mark.C);
                put("Introduction to Discrete Mathematics and Mathematical Logic", Mark.B);
                put("Declarative programming", Mark.A);
                put("Measurement practice", Mark.Z);
                put("Imperative Programming", Mark.A);
                put("Physical Culture and sport", Mark.Z);
                put("Physical Culture and sports (elective discipline)", Mark.Z);
                put("English", Mark.B);
                put("Digital platforms", Mark.B);
            }
        };
        Map<Semester, SubjectMark> grades = new HashMap<>() {
            {
                put(new Semester(1), new SubjectMark(firstSemesterGrades));
                put(new Semester(2), new SubjectMark(secondSemesterGrades));
            }
        };
        testBook = StudentBook.build(210638, "Computer Science and Engineering", 0, Mark.NULL,
                                     grades);
    }

    @Test
    void build() {
        StudentBook testBookNew;
        testBookNew = StudentBook.build(210638, "Computer Science and Engineering", 0);
        Assertions.assertEquals(testBookNew.getBookId(), 210638);
        Assertions.assertEquals(testBookNew.getSpecialization(),
                                "Computer Science and " + "Engineering");
        Assertions.assertEquals(testBookNew.getStatus(), 0);
    }

    @Test
    void testBuild() {
        StudentBook testBookNew;
        testBookNew = StudentBook.build(210638, "Computer Science and Engineering", 0, Mark.NULL);
        Assertions.assertEquals(testBookNew.getQualifyingWorkMark(), Mark.NULL);
    }

    @Test
    void testBuild1() {
        StudentBook testBookNew;
        testBookNew = StudentBook.build(210638, "Computer Science and Engineering", 0, Mark.NULL);
        Assertions.assertEquals(testBookNew.getBookId(), 210638);
        Assertions.assertEquals(testBookNew.getSpecialization(),
                                "Computer Science and " + "Engineering");
        Assertions.assertEquals(testBookNew.getStatus(), 0);
        Assertions.assertEquals(testBookNew.getQualifyingWorkMark(), Mark.NULL);
    }


    @Test
    void setSpecialization() throws NoSuchFieldException, IllegalAccessException {
        testBook.setSpecialization("kekes");
        Field field = testBook.getClass().getDeclaredField("specialization");
        field.setAccessible(true);
        Assertions.assertEquals("kekes", field.get(testBook));
    }


    @Test
    void setBookId() throws IllegalAccessException, NoSuchFieldException {
        testBook.setBookId(210748);
        Field field = testBook.getClass().getDeclaredField("bookId");
        field.setAccessible(true);
        Assertions.assertEquals(210748, field.get(testBook));
    }


    @Test
    void setQualifyingWorkMark() throws IllegalAccessException, NoSuchFieldException {
        testBook.setQualifyingWorkMark(Mark.B);
        Field field = testBook.getClass().getDeclaredField("qualifyingWorkMark");
        field.setAccessible(true);
        Assertions.assertEquals(Mark.B, field.get(testBook));
    }

    @Test
    void getAverageBookMark() {
        System.out.println(Math.abs(4.25 - testBook.getAverageBookMark()) < EPS);
    }

    @Test
    void getAverageDiplomaMark() {
        System.out.println(Math.abs(4.444444444444445 - testBook.getAverageDiplomaMark()) < EPS);
    }

    @Test
    void isRedDiploma() {
        Assertions.assertFalse(testBook.isRedDiploma());
    }

    @Test
    void isIncreasedScholarship() {
        Assertions.assertFalse(testBook.isIncreasedScholarship(7));
    }

    @Test
    void addMark() {
        Semester semesterThree = new Semester(3);
        testBook.addMark(semesterThree, "Object Oriented Programming", Mark.A);

        Assertions.assertEquals(testBook.getBookMark(semesterThree, "Object Oriented Programming"),
                                Mark.A);
    }
}