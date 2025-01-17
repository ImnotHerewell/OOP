package ru.nsu.valikov.models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;


/**
 * Student info.
 */
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    public static final Map<String, Set<LocalDate>> attendanceMap = new HashMap<>();
    public static final Map<String, Student> studentMap = new HashMap<>();

    static final String DEFAULT_BRANCH_NAME = "master";
    @NonNull
    String nickName;
    @NonNull
    String realName;
    @NonNull
    String repositoryUrl;
    @NonNull
    String branchName = DEFAULT_BRANCH_NAME;

}
