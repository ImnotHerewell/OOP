package ru.nsu.valikov;

public enum Mark {
    A(5), B(4), C(3), D(2), Z(0), NULL(-1);
    private final int value;
    private int retakeCount;

    Mark(int value) {
        this.value = value;
        this.retakeCount = 0;
    }

    public int getMark() {
        return value;
    }

    public int getRetakeCount() {
        return retakeCount;
    }

    public Mark setRetakeCount(int retakeCount) {
        this.retakeCount = retakeCount;
        return this;
    }
}
