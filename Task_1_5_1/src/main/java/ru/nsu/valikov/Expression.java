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
}
