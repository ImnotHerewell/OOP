//package ru.nsu.valikov;
//
//public class DoubleNum implements Expr {
//    private double number;
//    public double getNumber() {
//        return number;
//    }
//
//    public void setNumber(double number) {
//        this.number = number;
//    }
//    public Expr plus(DoubleNum numberA, ComplexNumber numberB) {
//        if (numberB.i() == 0) {
//            return new DoubleNum(plus(numberA.number(), numberB.re()));
//        }
//        return new ComplexNumber(plus(numberA.number(), numberB.re()), numberB.i());
//    }
//
//
//    public void plus(Degree degree) {
//        throw new IllegalArgumentException("Plus between double number and degrees isn't possible"
//                                           + ".");
//    }
//
//    public void plus(DoubleNum number) {
//        this.number += number.getNumber();
//    }
//}
