package ru.nsu.valikov.workers.chef.services;

/**
 * Yea, it cooks.
 */
public class CookService implements Cook {
    private final int workEfficiency;
    private final int time;
    private static final int MAX_ECE = 101;
    private static final long WAIT_MULTIPLIER = 10L;

    public CookService(int workEfficiency, int time) {
        this.workEfficiency = workEfficiency;
        this.time = time;
    }

    @Override
    public void cook() throws InterruptedException {
        Thread.sleep(time * WAIT_MULTIPLIER * (MAX_ECE - workEfficiency));
    }
}
