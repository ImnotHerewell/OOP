package ru.nsu.valikov.service.dto;

import lombok.NonNull;

public record CloneProjectRequest(@NonNull String uri, @NonNull String branch,
                                  @NonNull String student) {

}
