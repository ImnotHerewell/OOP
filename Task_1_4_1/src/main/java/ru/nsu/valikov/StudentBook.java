package ru.nsu.valikov;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Зачетная книжка
 * Реализовать класс электронная зачетной книжки студента ФИТ и обеспечить следующие
 * функции:
 * текущий средний бал за все время обучения;
 * может ли студент получить «красный» диплом с отличием;
 * будет ли повышенная стипендия в этом семестре.
 * Для первого теста используйте данные из Вашей зачётной книжки
 * Требования для диплома с отличием:
 * 75% оценок в приложении к диплому(последняя оценка) – «отлично»
 * Нет в зачетной книжке итоговых оценок «удовлетворительно»
 * Квалификационная работа защищена на «отлично»
 */
public class StudentBook implements RecordBook {
    private final Map<Semester, SubjectMark> bookGrades;
    private final SubjectMark diplomaGrades = new SubjectMark(new HashMap<>());
    private final int status;
    private int bookId;
    private String specialization;
    private int sumDiplomaMark;
    private int sumBookMark;
    private Mark qualifyingWorkMark;

    private StudentBook(int bookId, String specialization, int status, Mark qualifyingWorkMark,
                        Map<Semester, SubjectMark> bookGrades) {
        this.bookId = bookId;
        this.status = status;
        this.specialization = specialization;
        this.qualifyingWorkMark = qualifyingWorkMark;
        this.bookGrades = bookGrades;
        constructAverageGrades();
    }

    private void constructAverageGrades() {
        for (var semester : bookGrades.keySet()) {
            Map<String, Mark> subjectMark = bookGrades.get(semester).subjectMark();
            for (var subject : subjectMark.keySet()) {
                Mark mark = subjectMark.get(subject);
                sumBookMark += mark.getMark();
                diplomaGrades.subjectMark().put(subject, mark);
            }
        }
        for (var subject : diplomaGrades.subjectMark().keySet()) {
            sumDiplomaMark += diplomaGrades.subjectMark().get(subject).getMark();
        }
    }

    private long noBadMarks() {
        long badMarks = 0;
        for (var subject : bookGrades.entrySet()) {
            Stream<Map.Entry<String, Mark>> bookStream =
                    subject.getValue().subjectMark().entrySet().stream();
            badMarks += bookStream.filter(f -> f.getValue() == Mark.C ||
                                               f.getValue() == Mark.D).count();
        }
        return badMarks;
    }

    public void build(int bookId, String specialization, int status, Mark qualifyingWorkMark,
                      Map<Semester, SubjectMark> grades) {
        new StudentBook(bookId, specialization, status, qualifyingWorkMark, grades);
    }

    public void build(int bookId, String specialization, int status, Mark qualifyingWorkMark) {
        new StudentBook(bookId, specialization, status, qualifyingWorkMark, new HashMap<>());
    }

    public void build(int bookId, String specialization, int status) {
        new StudentBook(bookId, specialization, status, Mark.NULL, new HashMap<>());
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
        return (double) sumBookMark / bookGrades.size();
    }

    public double getAverageDiplomaMark() {
        return (double) sumDiplomaMark / diplomaGrades.subjectMark().size();
    }

    public boolean isRedDiploma() {
        final double EPS = 0.000000001;
        Stream<Map.Entry<String, Mark>> diplomaStream =
                diplomaGrades.subjectMark().entrySet().stream();
        double marksA = diplomaStream.filter(f -> f.getValue() == Mark.A).count();
        long marksBad = noBadMarks();
        return status == 0 && (Math.abs(marksA / diplomaGrades.subjectMark().size() - 0.75) < EPS &&
                               marksBad == 0 && qualifyingWorkMark == Mark.A);
    }

    public boolean isIncreasedScholarship(int points) {
        final int BORDER = 20;
        long badMarks = noBadMarks();
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
        return badMarks == 0 && ((status == 0 && semesterNumber.semesterNumber() >= 3) ||
                                 (status == 1 && semesterNumber.semesterNumber() >= 10) &&
                                 points > BORDER);
    }
}
