package ru.nsu.valikov;

public class ComplexNumber extends Expr {
    private double re;
    private double im;

    ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    double getValue() {
        return re;
    }

    double getSecond() {
        return im;
    }

    @Override
    void plus(Pair number) {
        if (number.b() == 1) {
            throw new IllegalArgumentException("Cannot perform operations with different types.");

        }
        re += number.a().getValue();
        im += number.a().getSecond();
    }

    @Override
    void minus(Pair number) {
        if (number.b() == 1) {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
        re -= number.a().getValue();
        im -= number.a().getSecond();
    }

    @Override
    void multiplication(Pair number) {
        if (number.b() == 1) {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
        double reNew = (re * number.a().getValue() - im * number.a().getSecond());
        im = (re * number.a().getSecond() + im * number.a().getValue());
        re = reNew;
    }

    @Override
    void division(Pair number) {
        if (number.b() == 1 || Math.pow(number.a().getValue(), 2) + Math.pow(number.a().getSecond(),
                                                                             2) == 0) {
            throw new IllegalArgumentException("Cannot perform operations with different types.");
        }
        double denominator = Math.pow(number.a().getValue(), 2) + Math.pow(number.a().getSecond(),
                                                                           2);
        double reNew = (re * number.a().getValue() + im * number.a().getSecond()) / denominator;
        im = (im * number.a().getValue() - re * number.a().getSecond()) / denominator;
        re = reNew;
    }

    @Override
    void pow(Pair number) {
        if (number.b() == 1) {
            throw new IllegalArgumentException();
        }
        if (number.a().getSecond() == 0 && im == 0) {
            re = Math.pow(re, number.a().getValue());
        } else {
            log();
            multiplication(number);
            double exp = Math.exp(re);
            re = exp * Math.cos(im);
            im = exp * Math.sin(im);
        }
    }

    @Override
    void log() {
        double r = Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
        if (r == 0 || re == 0) {
            throw new ArithmeticException();
        }
        im = Math.atan(im / re);
        re = Math.log(r);
    }

    @Override
    void sqrt() {
        double r = Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
        if (im != 0) {
            im = im / Math.abs(im) * Math.sqrt((-re + r) / 2);
        }
        re = Math.sqrt((re + r) / 2);
    }

    @Override
    ComplexNumber sin() {
        if (im != 0) {
            throw new ArithmeticException();
        }
        re = Math.sin(re);
        return this;
    }

    @Override
    ComplexNumber cos() {
        if (im != 0) {
            throw new ArithmeticException();
        }
        re = Math.cos(re);
        return this;
    }

}
