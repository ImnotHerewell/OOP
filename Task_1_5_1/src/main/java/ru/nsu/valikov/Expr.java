package ru.nsu.valikov;

interface Expr {
    void plus(ComplexNumber number);
    void plus(Degree number);
    void minus(ComplexNumber number);
    void minus(Degree number);

}
