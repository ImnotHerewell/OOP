package ru.nsu.valikov;

import static java.lang.Double.NaN;

/**
 * Degree (% not a percentage).
 * Cannot perform some operations between degrees and complex numbers.
 * Examples:
 * 1) 3%
 * 2) 3.13%
 */
public class Degree extends Expr {
    private double value;

    Degree(double value) {
        this.value = value;
    }

    /**
     * Degree cannot have negative value.
     */
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

    /**
     * Adding one degree to another, default.
     *
     * @param number another numeric data.
     */
    @Override
    void plus(Pair number) {
        if (number.b() == 0) {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
        value += number.a().getValue();
        checkCorrectness();
    }

    /**
     * Subtract one degree from another, default.
     *
     * @param number another numeric data.
     */
    @Override
    void minus(Pair number) {
        if (number.b() == 0) {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
        value -= number.a().getValue();
        checkCorrectness();
    }

    /**
     * Multiply one degree by another double number.
     *
     * @param number another numeric data.
     */
    @Override
    void multiplication(Pair number) {
        if (number.b() == 1 || number.a().getSecond() != 0) {
            throw new IllegalArgumentException(
                    "Cannot multiply degrees with degrees or complex numbers.");
        }
        value *= number.a().getValue();
        checkCorrectness();
    }

    /**
     * Divide one degree by another double number.
     *
     * @param number another numeric data.
     */
    @Override
    void division(Pair number) {
        if (number.b() == 1 || number.a().getSecond() != 0) {
            throw new IllegalArgumentException(
                    "Cannot divide degrees by degrees or complex numbers.");
        }
        value /= number.a().getValue();
        checkCorrectness();
    }

    /**
     * Raising one degree to a double number.
     *
     * @param number number another numeric data.
     */
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

    /**
     * Take sin function from degree, default.
     *
     * @return new double number.
     */
    @Override
    ComplexNumber sin() {
        if (value % 180 == 0) {
            return new ComplexNumber(0, 0);
        }
        return new ComplexNumber(Math.sin(Math.toRadians(value % 360)), 0);
    }

    /**
     * Take cos function from degree, default.
     *
     * @return new double number.
     */
    @Override
    ComplexNumber cos() {
        if (value % 180 == 90) {
            return new ComplexNumber(0, 0);
        }
        return new ComplexNumber(Math.cos(Math.toRadians(value % 360)), 0);
    }

}
