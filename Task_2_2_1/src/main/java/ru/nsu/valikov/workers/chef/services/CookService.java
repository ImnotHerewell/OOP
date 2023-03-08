package ru.nsu.valikov.workers.chef.services;

/**
 * Yea, it cooks.
 */
public class CookService implements Cook {
    private final int workEfficiency;
    private final int time;

    public CookService(int workEfficiency, int time) {
        this.workEfficiency = workEfficiency;
        this.time = time;
    }

    @Override
    public void cook() throws InterruptedException {
        Thread.sleep(time * 10L * (101 - workEfficiency));
    }
}
