package ru.nsu.valikov;

/**
 * Class for detecting is it degree or double/complex number.
 *
 * @param a Complex number/double or degree.
 * @param b if Complex then complex number, if Degree then degree.
 */
public record Pair(Expr a, DataType b) {
}
