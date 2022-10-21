package ru.nsu.valikov;

public enum Mark {
    A(5), B(4), C(3), D(2), NULL(-1);
    private final int value;
    private int retakeCount;

    Mark(int value, int retakeCount) {
        this.value = value;
        this.retakeCount = retakeCount;
    }

    Mark(int value) {
        this.value = value;
    }

    public int getMark() {
        return value;
    }

    public void setRetakeCount(int retakeCount) {
        this.retakeCount = retakeCount;
    }

    public int getRetakeCount() {
        return retakeCount;
    }
}
