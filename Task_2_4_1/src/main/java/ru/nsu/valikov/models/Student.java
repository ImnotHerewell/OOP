package ru.nsu.valikov.models;

import java.util.HashSet;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;


@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    static final HashSet<String> NICKNAMES = new HashSet<>();
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
