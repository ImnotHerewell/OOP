package ru.nsu.valikov.models;

import java.net.URL;
import java.util.HashSet;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;


@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    static final HashSet<String> NICKNAMES = new HashSet<>();
    static final String DEFAULT_BRANCH_NAME = "master";
    @NonNull
    String nickname;
    @NonNull
    String realName;
    @NonNull
    URL branchName;
}
