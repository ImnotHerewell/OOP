package ru.nsu.valikov.service;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import java.net.URISyntaxException;
import java.util.Objects;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GroovyExecutor {

    @SneakyThrows
    public void execute(@NonNull String filename) {
        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(
            Objects.requireNonNull(
                GroovyExecutor.class.getClassLoader().getResource(filename)).toURI());
        script.run();
    }
}
