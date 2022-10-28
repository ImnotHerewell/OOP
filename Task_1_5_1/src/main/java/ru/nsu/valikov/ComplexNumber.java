package ru.nsu.valikov;

public class ComplexNumber extends Expr {
    private double re;
    private double i;

    ComplexNumber(double re, double i) {
        this.re = re;
        this.i = i;
    }

    public double getValue() {
        return re;
    }

    public double getSecond() {
        return i;
    }

    @Override
    public void plus(Pair number) {
        if (number.b() == 0) {
            re += number.a().getValue();
            i += number.a().getSecond();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    public void minus(Pair number) {
        if (number.b() == 0) {
            re -= number.a().getValue();
            i -= number.a().getSecond();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

}
