package ru.nsu.valikov.service;

import lombok.Getter;
import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.ResultHandler;

public class Result<T> implements ResultHandler<T> {

    @Getter
    private T state;

    @Override
    public void onComplete(T result) {
        state=result;
    }

    @Override
    public void onFailure(GradleConnectionException failure) {

    }
}
