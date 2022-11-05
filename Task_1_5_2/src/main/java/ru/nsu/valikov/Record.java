package ru.nsu.valikov;

import com.fasterxml.jackson.annotation.JsonProperty;

record Record(@JsonProperty("title") String title,
        @JsonProperty("description") String description) {
}
