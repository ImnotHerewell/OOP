package ru.nsu.valikov;

import java.io.File;

public class Configuration {

    public static final File DEFAULT_TASKS_DIRECTORY = new File("boys");

    static {
        if (!DEFAULT_TASKS_DIRECTORY.exists()) {
            DEFAULT_TASKS_DIRECTORY.mkdirs();
        }
    }

}
