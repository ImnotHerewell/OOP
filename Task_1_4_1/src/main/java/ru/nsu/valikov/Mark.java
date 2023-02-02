package ru.nsu.valikov;


/**
 * Mark which student can get.
 */
public enum Mark {
    /**
     * English marks - Russian marks.
     * A - 5;
     * B - 4;
     * C - 3;
     * D - 2;
     * Z - offset;
     * NULL - don't have mark, using only for qualifying work.
     */
    A(5), B(4), C(3), D(2), Z(0), NULL(-1);
    private final int value;

    /**
     * How many retakes' student had before getting default mark.
     **/
    private int retakeCount;

    /**
     * Private constructor for enum.
     *
     * @param value russian mark's value;
     */
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
