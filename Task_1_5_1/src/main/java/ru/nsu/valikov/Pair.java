package ru.nsu.valikov;

/**
 * Class for detecting is it degree or double/complex number.
 *
 * @param a Complex number/double or degree.
 * @param b if 1 then degree else double/complex number.
 */
public record Pair(Expr a, int b) {
}
