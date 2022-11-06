package ru.nsu.valikov;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RECORD.
 *
 * @param title TITLE
 * @param description DESCRIPTION
 */
record Record(@JsonProperty("title") String title,
        @JsonProperty("description") String description) {
}
