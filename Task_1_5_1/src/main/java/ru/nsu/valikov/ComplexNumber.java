package ru.nsu.valikov;

public class ComplexNumber extends Expr {
    private double re;
    private double i;

    ComplexNumber(double re, double i) {
        this.re = re;
        this.i = i;
    }

    double getValue() {
        return re;
    }

    double getSecond() {
        return i;
    }

    @Override
    void plus(Pair number) {
        if (number.b() == 0) {
            re += number.a().getValue();
            i += number.a().getSecond();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    void minus(Pair number) {
        if (number.b() == 0) {
            re -= number.a().getValue();
            i -= number.a().getSecond();
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    void multiplication(Pair number) {
        if (number.b() == 0) {
            double reNew = (re * number.a().getValue() - i * number.a().getSecond());
            i = (re * number.a().getSecond() + i * number.a().getValue());
            re = reNew;
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    void division(Pair number) {
        if (number.b() == 0 && Math.pow(number.a().getValue(), 2) + Math.pow(number.a().getSecond(),
                                                                             2) != 0) {
            double denominator = Math.pow(number.a().getValue(), 2) + Math.pow(
                    number.a().getSecond(), 2);
            double reNew = (re * number.a().getValue() + i * number.a().getSecond()) / denominator;
            i = (i * number.a().getValue() - re * number.a().getSecond()) / denominator;
            re = reNew;
        } else {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
    }

    @Override
    void pow(Pair number) {
        if (number.b() == 0) {
            if (number.a().getSecond() == 0 && i == 0) {
                re = Math.pow(re, number.a().getValue());
            } else {
                log();
                multiplication(number);
                double exp = Math.exp(re);
                re = exp * Math.cos(i);
                i = exp * Math.sin(i);
                //                System.out.println(re + " " + i);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    void log() {
        double r = Math.sqrt(Math.pow(re, 2) + Math.pow(i, 2));
        i = Math.atan(i / re);
        re = Math.log(r);
    }

    @Override
    void sqrt() {
        double r = Math.sqrt(Math.pow(re, 2) + Math.pow(i, 2));
        if (i != 0) {
            i = i / Math.abs(i) * Math.sqrt((-re + r) / 2);
        }
        re = Math.sqrt((re + r) / 2);
        System.out.println(re + " " + i);
    }

    @Override
    ComplexNumber sin() {
        return null;
    }

    @Override
    ComplexNumber cos() {
        return null;
    }

}
