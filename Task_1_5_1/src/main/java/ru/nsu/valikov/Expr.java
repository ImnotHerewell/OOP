package ru.nsu.valikov;

abstract class Expr {
    abstract double getValue();
    abstract double getSecond();
    abstract void plus(Pair expNumber);
    abstract void minus(Pair expNumber);

}
