package ru.nsu.valikov;

class Expression {
    static double plus(double numberA, double numberB) {
        return numberA + numberB;
    }

    static double minus(double numberA, double numberB) {
        return plus(numberA, -numberB);
    }

    static double multiplication(double numberA, double numberB) {
        return numberA * numberB;
    }

    static double division(double numberA, double numberB) {
        return numberA / numberB;
    }

    static double pow(double numberA, double numberB) {
        return Math.pow(numberA, numberB);
    }

    static double log(double numberA, double numberB) {
        return Math.log(numberB) / Math.log(numberA);
    }

    static double sqrt(double number) {
        return Math.sqrt(number);
    }

    static double sin(double degree) {
        degree %= 360;
        return Math.sin(degree);
    }

    static double cos(double degree) {
        degree %= 360;
        return Math.cos(degree);
    }

    static Expr plus(DoubleNum numberA, DoubleNum numberB) {
        return new DoubleNum(plus(numberA.number(), numberB.number()));
    }

    static Expr plus(DoubleNum numberA, ComplexNumber numberB) {
        if (numberB.i() == 0) {
            return new DoubleNum(plus(numberA.number(), numberB.re()));
        }
        return new ComplexNumber(plus(numberA.number(), numberB.re()), numberB.i());
    }

    static Expr plus(ComplexNumber numberA, DoubleNum numberB) {
        return plus(numberB, numberA);
    }

    static void plus(DoubleNum numberA, Degree numberB) {
        throw new IllegalArgumentException("Plus between float number and degrees isn't possible.");
    }

    static void plus(Degree numberA, DoubleNum numberB) {
        plus(numberB, numberA);
    }

    static Expr plus(ComplexNumber numberA, ComplexNumber numberB) {
        if (plus(numberA.i(), numberB.i()) == 0) {
            return new DoubleNum(plus(numberA.re(), numberB.re()));
        }
        return new ComplexNumber(plus(numberA.re(), numberA.re()), plus(numberA.i(), numberB.i()));
    }

    static void plus(ComplexNumber numberA, Degree numberB) {
        throw new IllegalArgumentException(
                "Plus between complex number and degrees isn't possible.");
    }

    static void plus(Degree numberA, ComplexNumber numberB) {
        plus(numberB, numberA);
    }

    static Degree plus(Degree numberA, Degree numberB) {
        return new Degree(plus(numberA.number(), numberB.number()) % 360);
    }

    static DoubleNum minus(DoubleNum numberA, DoubleNum numberB) {
        return new DoubleNum(minus(numberA.number(), numberB.number()));
    }

    static Expr minus(DoubleNum numberA, ComplexNumber numberB) {
        return plus(numberA, new ComplexNumber(-numberB.re(), -numberB.i()));
    }

    static Expr minus(ComplexNumber numberA, DoubleNum numberB) {
        return plus(numberA, new DoubleNum(-numberB.number()));
    }

    static void minus(DoubleNum numberA, Degree numberB) {
        throw new IllegalArgumentException(
                "Minus between float number and degrees isn't possible" + ".");
    }

    static void minus(Degree numberA, DoubleNum numberB) {
        minus(numberB, numberA);
    }

    static Expr minus(ComplexNumber numberA, ComplexNumber numberB) {
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
        return plus(numberA, new Degree(-numberB.number()));
    }

}
