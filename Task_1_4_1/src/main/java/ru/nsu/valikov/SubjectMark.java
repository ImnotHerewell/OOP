package ru.nsu.valikov;

import java.util.Map;

/**
 * Record like wrapper for map of subjects and its final marks.
 *
 * @param subjectMark map, which represents subject and its mark
 */
public record SubjectMark(Map<String, Mark> subjectMark) {
}
