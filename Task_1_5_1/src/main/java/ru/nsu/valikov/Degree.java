package ru.nsu.valikov;

import static java.lang.Double.NaN;

public class Degree extends Expr {
    private double value;

    Degree(double value) {
        this.value = value;
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
        if (number.b() == 1) {
            value += number.a().getValue();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    void minus(Pair number) {
        if (number.b() == 1) {
            value -= number.a().getValue();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    void multiplication(Pair number) {
        if (number.b() == 1) {
            value *= number.a().getValue();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    void division(Pair number) {
        if (number.b() == 1 && number.a().getValue() != 0) {
            value /= number.a().getValue();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    void pow(Pair number) {
        if (number.b() == 0 && number.a().getSecond() == 0) {
            value = Math.pow(value, number.a().getValue());
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    void log() {
        value = Math.log(value);
    }

    @Override
    void sqrt() {
        value = Math.sqrt(value);
    }

    @Override
    ComplexNumber sin() {
        return new ComplexNumber(Math.sin(Math.toRadians(value % 360)), 0);
    }

    @Override
    ComplexNumber cos() {
        return new ComplexNumber(Math.cos(Math.toRadians(value % 360)), 0);
    }

}
