package ru.nsu.valikov;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

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
    private final SortedMap<Semester, SubjectMark> bookGrades;
    /**
     * For easily usability, stores every final mark in diploma.
     */
    private final SubjectMark diplomaGrades = new SubjectMark(new TreeMap<>());
    /**
     * Bachelor or Master.
     */
    private Degree status;
    private int bookId;
    private String specialization;
    private int sumDiplomaMark;
    private int countDiplomaNoZsubjects;
    private int sumBookMark;
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
    private StudentBook(int bookId, String specialization, Degree status, Mark qualifyingWorkMark,
                        SortedMap<Semester, SubjectMark> bookGrades) {
        this.bookId = bookId;
        this.specialization = specialization;
        this.status = status;
        this.qualifyingWorkMark = qualifyingWorkMark;
        this.bookGrades = bookGrades;
        constructAverageGrades();
    }

    public static StudentBook build(int bookId, String specialization, Degree status,
                                    Mark qualifyingWorkMark,
                                    SortedMap<Semester, SubjectMark> grades) {
        return new StudentBook(bookId, specialization, status, qualifyingWorkMark, grades);
    }

    public static StudentBook build(int bookId, String specialization, Degree status,
                                    Mark qualifyingWorkMark) {
        return new StudentBook(bookId, specialization, status, qualifyingWorkMark, new TreeMap<>());
    }

    public static StudentBook build(int bookId, String specialization, Degree status) {
        return new StudentBook(bookId, specialization, status, Mark.NULL, new TreeMap<>());
    }

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
            var bookStream = subject.getValue().subjectMark().entrySet().stream();
            badMarks += bookStream.filter(f -> f.getValue() == Mark.C || f.getValue() == Mark.D)
                                  .count();
        }
        return badMarks == 0;
    }

    public Degree getStatus() {
        return status;
    }

    public void setStatus(Degree status) {
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
            bookGrades.put(semester, new SubjectMark(new TreeMap<>()));
        }
        Map<String, Mark> semesterGrades = bookGrades.get(semester).subjectMark();
        if (semesterGrades.containsKey(subject) && semesterGrades.get(subject) != Mark.Z) {
            sumBookMark -= semesterGrades.get(subject).getMark();
            countBookNoZsubjects--;
        }
        semesterGrades.put(subject, mark);
        sumBookMark += mark.getMark();
        if (diplomaGrades.subjectMark().containsKey(subject)
            && diplomaGrades.subjectMark().get(subject) != Mark.Z) {
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
        var diplomaStream = diplomaGrades.subjectMark().entrySet().stream();
        double marksA = diplomaStream.filter(f -> f.getValue() == Mark.A).count();
        return status == Degree.Bachelor && (
                Math.abs(marksA / diplomaGrades.subjectMark().size() - 0.75) < EPS && noBadMarks()
                && qualifyingWorkMark == Mark.A);
    }

    /**
     * Check if student can pretend on increased scholarship.
     * <a href="https://www.nsu.ru/n/education/stipendii.php#ID_999">...</a>
     *
     * @param points how many bonus points student has
     * @return true if all nsu conditions are satisfied, else false
     */
    public boolean isIncreasedScholarship(int points) {
        Semester semesterNumber = bookGrades.lastKey();
        Map<String, Mark> semesterMarks = bookGrades.get(semesterNumber).subjectMark();
        for (var subject : semesterMarks.entrySet()) {
            if (subject.getValue().getRetakeCount() > 0) {
                return false;
            }
        }
        return noBadMarks() && ((status == Degree.Bachelor && semesterNumber.semesterNumber() >= 3)
                                ||
                                (status == Degree.Master && semesterNumber.semesterNumber() >= 10)
                                && points > BORDER);
    }
}
