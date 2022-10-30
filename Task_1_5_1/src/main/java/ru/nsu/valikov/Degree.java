package ru.nsu.valikov;

import static java.lang.Double.NaN;

public class Degree extends Expr {
    private double value;

    Degree(double value) {
        this.value = value;
    }

    private void checkCorrectness() {
        if (value < 0) {
            throw new RuntimeException("Degrees cannot be negative!");
        }
    }

    double getValue() {
        return value;
    }

    @Override
    double getSecond() {
        return NaN;
    }

    @Override
    void plus(Pair number) {
        if (number.b() == 0) {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
        value += number.a().getValue();
        checkCorrectness();
    }

    @Override
    void minus(Pair number) {
        if (number.b() == 0) {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
        value -= number.a().getValue();
        checkCorrectness();
    }

    @Override
    void multiplication(Pair number) {
        if (number.b() == 1 || number.a().getSecond() != 0) {
            throw new IllegalArgumentException(
                    "Cannot multiply degrees with degrees or complex numbers.");
        }
        value *= number.a().getValue();
        checkCorrectness();
    }

    @Override
    void division(Pair number) {
        if (number.b() == 1 || number.a().getSecond() != 0) {
            throw new IllegalArgumentException(
                    "Cannot divide degrees by degrees or complex numbers.");
        }
        value /= number.a().getValue();
        checkCorrectness();
    }

    @Override
    void pow(Pair number) {
        if (number.b() == 1 || number.a().getSecond() != 0) {
            throw new IllegalArgumentException(
                    "Cannot raising degrees to degrees degree or complex number degree.");
        }
        value = Math.pow(value, number.a().getValue());
        checkCorrectness();
    }

    @Override
    void log() {
        value = Math.log(value);
        checkCorrectness();
    }

    @Override
    void sqrt() {
        value = Math.sqrt(value);
        checkCorrectness();
    }

    @Override
    ComplexNumber sin() {
        if (value % 180 == 0) {
            return new ComplexNumber(0, 0);
        }
        return new ComplexNumber(Math.sin(Math.toRadians(value % 360)), 0);
    }

    @Override
    ComplexNumber cos() {
        if (value % 180 == 90) {
            return new ComplexNumber(0, 0);
        }
        return new ComplexNumber(Math.cos(Math.toRadians(value % 360)), 0);
    }

}
