package ru.nsu.valikov;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for StudentBook class.
 */
class StudentBookTest {
    /**
     * To compare two doubles.
     */
    private static final double EPS = 0.0000000001;
    private final Semester semesterOne = new Semester(1);
    private final Semester semesterTwo = new Semester(2);
    private StudentBook testBook;

    /**
     * Initialize student book.
     */
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
                put("Declarative Programming", Mark.A);
                put("Measurement practice", Mark.Z);
                put("Imperative Programming", Mark.A);
                put("Physical Culture and sport", Mark.Z);
                put("Physical Culture and sports (elective discipline)", Mark.Z);
                put("English", Mark.B);
                put("Digital platforms", Mark.B);
            }
        };
        SortedMap<Semester, SubjectMark> grades = new TreeMap<>() {
            {
                put(semesterOne, new SubjectMark(firstSemesterGrades));
                put(semesterTwo, new SubjectMark(secondSemesterGrades));
            }
        };
        testBook = StudentBook.build(210638, "Computer Science and Engineering", Degree.Bachelor,
                Mark.NULL, grades);
    }

    /**
     * Test builder without grades and qualifying work.
     */
    @Test
    void build() {
        StudentBook testBookNew;
        testBookNew =
                StudentBook.build(210638, "Computer Science and Engineering", Degree.Bachelor);
        Assertions.assertEquals(testBookNew.getBookId(), 210638);
        Assertions.assertEquals(testBookNew.getSpecialization(),
                "Computer Science and " + "Engineering");
        Assertions.assertEquals(testBookNew.getStatus(), Degree.Bachelor);
    }

    /**
     * Test builder without grades.
     */
    @Test
    void testBuild() {
        StudentBook testBookNew;
        testBookNew = StudentBook.build(210638, "Computer Science and Engineering", Degree.Bachelor,
                Mark.NULL);
        Assertions.assertEquals(testBookNew.getQualifyingWorkMark(), Mark.NULL);
    }

    /**
     * Another builder test without grades.
     */
    @Test
    void testBuild1() {
        StudentBook testBookNew;
        testBookNew = StudentBook.build(210638, "Computer Science and Engineering", Degree.Bachelor,
                Mark.NULL);
        Assertions.assertEquals(testBookNew.getBookId(), 210638);
        Assertions.assertEquals(testBookNew.getSpecialization(),
                "Computer Science and " + "Engineering");
        Assertions.assertEquals(testBookNew.getStatus(), Degree.Bachelor);
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
    void setStatusTest() throws NoSuchFieldException, IllegalAccessException {
        testBook.setStatus(Degree.Master);
        Field field = testBook.getClass().getDeclaredField("status");
        field.setAccessible(true);
        Assertions.assertEquals(Degree.Master, field.get(testBook));
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

    @Test
    void setNewMark() {
        testBook.addMark(semesterTwo, "Imperative Programming", Mark.B);
        Assertions.assertEquals(testBook.getBookMark(semesterTwo, "Imperative Programming"),
                Mark.B);
    }
}