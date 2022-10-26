package ru.nsu.valikov;

public class Expression {
    static double plus(double numberA, double numberB) {
        return numberA + numberB;
    }

    static ComplexNumber plus(double numberA, ComplexNumber numberB) {
        return new ComplexNumber(numberA + numberB.re(), numberB.i());
    }

    static ComplexNumber plus(ComplexNumber numberA, double numberB) {
        return plus(numberB, numberA);
    }

    static void plus(double numberA, Degree numberB) {
        throw new IllegalArgumentException("Plus between float number and degrees isn't possible.");
    }

    static void plus(Degree numberA, double numberB) {
        plus(numberB, numberA);
    }

    static ComplexNumber plus(ComplexNumber numberA, ComplexNumber numberB) {
        return new ComplexNumber(numberA.re() + numberA.re(), numberA.i() + numberB.i());
    }

    static void plus(ComplexNumber numberA, Degree numberB) {
        throw new IllegalArgumentException(
                "Plus between complex number and degrees isn't possible.");
    }

    static void plus(Degree numberA, ComplexNumber numberB) {
        plus(numberB, numberA);
    }

    static Degree plus(Degree numberA, Degree numberB) {
        return new Degree((numberA.number() + numberB.number()) % 360);
    }

    static double minus(double numberA, double numberB) {
        return numberA - numberB;
    }

    static ComplexNumber minus(double numberA, ComplexNumber numberB) {
        return plus(numberA, new ComplexNumber(-numberB.re(), -numberB.i()));
    }

    static ComplexNumber minus(ComplexNumber numberA, double numberB) {
        return plus(numberA, -numberB);
    }

    static void minus(double numberA, Degree numberB) {
        throw new IllegalArgumentException(
                "Minus between float number and degrees isn't possible" + ".");
    }

    static void minus(Degree numberA, double numberB) {
        minus(numberB, numberA);
    }

    static ComplexNumber minus(ComplexNumber numberA, ComplexNumber numberB) {
        return plus(numberA, new ComplexNumber(-numberA.i(), -numberB.i()));
    }

    static void minus(ComplexNumber numberA, Degree numberB) {
        throw new IllegalArgumentException(
                "Minus between complex number and degrees isn't possible.");
    }

    static void minus(Degree numberA, ComplexNumber numberB) {
        minus(numberB, numberA);
    }

    static Degree minus(Degree numberA, Degree numberB) {
        return plus(numberA,new Degree(-numberB.number()));
    }

}
