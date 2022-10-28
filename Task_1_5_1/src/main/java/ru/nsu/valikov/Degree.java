package ru.nsu.valikov;

public class Degree extends Expr {
    private double value;

    Degree(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    double getSecond() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void plus(Pair number) {
        if (number.b() == 1) {
            value += number.a().getValue();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    public void minus(Pair number) {
        if (number.b() == 1) {
            value -= number.a().getValue();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

}
