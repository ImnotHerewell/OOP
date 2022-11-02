package ru.nsu.valikov;

/**
 * Interface-like class for any numeric data, marker.
 */
abstract class Expr {
    abstract double getValue();

    abstract double getSecond();

    abstract void plus(Pair expNumber);

    abstract void minus(Pair expNumber);

    abstract void multiplication(Pair expNumber);

    abstract void division(Pair expNumber);

    abstract void pow(Pair expNumber);

    abstract void log();

    abstract void sqrt();

    abstract ComplexNumber sin();

    abstract ComplexNumber cos();
}
