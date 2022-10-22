package ru.nsu.valikov;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
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
    private int status;
    private int bookId;
    private String specialization;
    private int sumDiplomaMark;
    private int countDiplomaNoZsubjects;
    private int sumBookMark;
    private int countBookNoZsubjects;
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

    private long noBadMarks() {
        long badMarks = 0;
        for (var subject : bookGrades.entrySet()) {
            Stream<Map.Entry<String, Mark>> bookStream =
                    subject.getValue().subjectMark().entrySet().stream();
            badMarks += bookStream.filter(f -> f.getValue() == Mark.C
                                               || f.getValue() == Mark.D).count();
        }
        return badMarks;
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

    public Mark getBookMark(Semester semester, String subject) {
        if (!bookGrades.containsKey(semester)
            || !bookGrades.get(semester).subjectMark().containsKey(subject)) {
            throw new NoSuchElementException();
        }
        return bookGrades.get(semester).subjectMark().get(subject);
    }

    public boolean isRedDiploma() {
        final double EPS = 0.000000001;
        Stream<Map.Entry<String, Mark>> diplomaStream =
                diplomaGrades.subjectMark().entrySet().stream();
        double marksA = diplomaStream.filter(f -> f.getValue() == Mark.A).count();
        long marksBad = noBadMarks();
        return status == 0 && (Math.abs(marksA / diplomaGrades.subjectMark().size() - 0.75) < EPS
                               && marksBad == 0 && qualifyingWorkMark == Mark.A);
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
        return badMarks == 0 && ((status == 0 && semesterNumber.semesterNumber() >= 3)
                                 || (status == 1 && semesterNumber.semesterNumber() >= 10)
                                    && points > BORDER);
    }
}
