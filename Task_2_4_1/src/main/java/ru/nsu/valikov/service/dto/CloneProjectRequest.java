package ru.nsu.valikov.service.dto;

import lombok.NonNull;

/**
 * Not hehe, goho.
 *
 * @param uri     repository url
 * @param branch  name
 * @param student nickname
 * @param task    name
 */
public record CloneProjectRequest(@NonNull String uri, @NonNull String branch,
                                  @NonNull String student, @NonNull String task) {

}
