package ru.nsu.valikov;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * Student book class, where grades are stored.
 */
public class StudentBook implements RecordBook {
    /**
     * If student has more than BORDER points, he can pretend on increased scholarship.
     */
    private static final int BORDER = 20;
    /**
     * To compare two doubles.
     */
    private static final double EPS = 0.000000001;
    /**
     * For easily usability, stores every mark in book.
     */
    private final Map<Semester, SubjectMark> bookGrades;
    /**
     * For easily usability, stores every final mark in diploma.
     */
    private final SubjectMark diplomaGrades = new SubjectMark(new HashMap<>());
    /**
     * 0 if bachelor,
     * 1 if master.
     */
    private int status;
    private int bookId;
    private String specialization;
    /**
     * Sum of all marks in diploma.
     */
    private int sumDiplomaMark;
    /**
     * Count of all subjects in diploma with no Mark.Z value.
     */
    private int countDiplomaNoZsubjects;
    /**
     * Sum of all marks in book.
     */
    private int sumBookMark;
    /**
     * Count of all subjects in book with no Mark.Z value.
     */
    private int countBookNoZsubjects;
    private Mark qualifyingWorkMark;

    /**
     * Private constructor.
     *
     * @param bookId             book id
     * @param specialization     specialization
     * @param status             bachelor or master
     * @param qualifyingWorkMark qualifying work mark
     * @param bookGrades         map with all marks
     */
    private StudentBook(int bookId, String specialization, int status, Mark qualifyingWorkMark,
                        Map<Semester, SubjectMark> bookGrades) {
        this.bookId = bookId;
        this.status = status;
        this.specialization = specialization;
        this.qualifyingWorkMark = qualifyingWorkMark;
        this.bookGrades = bookGrades;
        constructAverageGrades();
    }

    public static StudentBook build(int bookId, String specialization, int status,
                                    Mark qualifyingWorkMark, Map<Semester, SubjectMark> grades) {
        return new StudentBook(bookId, specialization, status, qualifyingWorkMark, grades);
    }

    public static StudentBook build(int bookId, String specialization, int status,
                                    Mark qualifyingWorkMark) {
        return new StudentBook(bookId, specialization, status, qualifyingWorkMark, new HashMap<>());
    }

    public static StudentBook build(int bookId, String specialization, int status) {
        return new StudentBook(bookId, specialization, status, Mark.NULL, new HashMap<>());
    }

    /**
     * Get average grades from constructor.
     */
    private void constructAverageGrades() {
        for (var semester : bookGrades.keySet()) {
            Map<String, Mark> subjectMark = bookGrades.get(semester).subjectMark();
            for (var subject : subjectMark.keySet()) {
                Mark mark = subjectMark.get(subject);
                if (mark != Mark.Z) {
                    sumBookMark += mark.getMark();
                    countBookNoZsubjects++;
                }
                diplomaGrades.subjectMark().put(subject, mark);
            }
        }
        for (var subject : diplomaGrades.subjectMark().keySet()) {
            Mark mark = diplomaGrades.subjectMark().get(subject);
            sumDiplomaMark += mark.getMark();
            if (mark != Mark.Z) {
                countDiplomaNoZsubjects++;
            }
        }
    }

    /**
     * Check if student have any C (or D) marks.
     *
     * @return true if student doesn't have any C (or D) marks, else false
     */
    private boolean noBadMarks() {
        long badMarks = 0;
        for (var subject : bookGrades.entrySet()) {
            Stream<Map.Entry<String, Mark>> bookStream =
                    subject.getValue().subjectMark().entrySet().stream();
            badMarks += bookStream.filter(f -> f.getValue() == Mark.C || f.getValue() == Mark.D)
                                  .count();
        }
        return badMarks == 0;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Mark getQualifyingWorkMark() {
        return qualifyingWorkMark;
    }

    public void setQualifyingWorkMark(Mark qualifyingWorkMark) {
        this.qualifyingWorkMark = qualifyingWorkMark;
    }

    public double getAverageBookMark() {
        return (double) sumBookMark / countBookNoZsubjects;
    }

    public double getAverageDiplomaMark() {
        return (double) sumDiplomaMark / countDiplomaNoZsubjects;
    }

    /**
     * Add new mark to book.
     *
     * @param semester semester's number
     * @param subject  subject's name
     * @param mark     mark
     */
    public void addMark(Semester semester, String subject, Mark mark) {
        if (!bookGrades.containsKey(semester)) {
            bookGrades.put(semester, new SubjectMark(new HashMap<>()));
        }
        Map<String, Mark> semesterGrades = bookGrades.get(semester).subjectMark();
        if (semesterGrades.containsKey(subject) && semesterGrades.get(subject) != Mark.Z) {
            sumBookMark -= semesterGrades.get(subject).getMark();
            countBookNoZsubjects--;
        }
        semesterGrades.put(subject, mark);
        sumBookMark += mark.getMark();
        if (diplomaGrades.subjectMark().containsKey(subject) && diplomaGrades.subjectMark().get(
                subject) != Mark.Z) {
            sumDiplomaMark -= diplomaGrades.subjectMark().get(subject).getMark();
            countDiplomaNoZsubjects--;
        }
        diplomaGrades.subjectMark().put(subject, mark);
        sumDiplomaMark += mark.getMark();
        if (mark != Mark.Z) {
            countDiplomaNoZsubjects++;
            countBookNoZsubjects++;
        }
    }

    /**
     * Get mark from student book.
     *
     * @param semester semester number
     * @param subject  subject name
     * @return mark
     */
    public Mark getBookMark(Semester semester, String subject) {
        if (!bookGrades.containsKey(semester) || !bookGrades.get(semester).subjectMark()
                                                            .containsKey(subject)) {
            throw new NoSuchElementException();
        }
        return bookGrades.get(semester).subjectMark().get(subject);
    }

    /**
     * Check if student can pretend on red diploma.
     *
     * @return true if all red diploma (task) conditions are satisfied, else false
     */
    public boolean isRedDiploma() {
        Stream<Map.Entry<String, Mark>> diplomaStream =
                diplomaGrades.subjectMark().entrySet().stream();
        double marksA = diplomaStream.filter(f -> f.getValue() == Mark.A).count();
        return status == 0 && (Math.abs(marksA / diplomaGrades.subjectMark().size() - 0.75) < EPS
                               && noBadMarks() && qualifyingWorkMark == Mark.A);
    }

    /**
     * Check if student can pretend on increased scholarship.
     * <a href="https://www.nsu.ru/n/education/stipendii.php#ID_999">...</a>
     *
     * @param points how many bonus points student has
     * @return true if all nsu conditions are satisfied, else false
     */
    public boolean isIncreasedScholarship(int points) {
        Semester semesterNumber = new Semester(-1);
        for (int semester = 20; semester > 0; semester--) {
            semesterNumber = new Semester(semester);
            if (bookGrades.containsKey(semesterNumber)) {
                break;
            }
        }
        Map<String, Mark> semesterMarks = bookGrades.get(semesterNumber).subjectMark();
        for (var subject : semesterMarks.entrySet()) {
            if (subject.getValue().getRetakeCount() > 0) {
                return false;
            }
        }
        return noBadMarks() && ((status == 0 && semesterNumber.semesterNumber() >= 3)
                                || (status == 1 && semesterNumber.semesterNumber() >= 10)
                                   && points > BORDER);
    }
}
