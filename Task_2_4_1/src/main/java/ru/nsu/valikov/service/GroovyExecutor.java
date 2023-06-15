package ru.nsu.valikov.service;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import java.nio.file.NoSuchFileException;
import java.util.Objects;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GroovyExecutor {

    @SneakyThrows
    public void execute(@NonNull String filename) {
        GroovyShell shell = new GroovyShell();
        try {
            Script script = shell.parse(
                Objects.requireNonNull(
                    GroovyExecutor.class.getClassLoader().getResource(filename)).toURI());
            script.run();
        } catch (NullPointerException e) {
            throw new NoSuchFileException(filename + " is not presented at resources folder.");
        }
    }
}
