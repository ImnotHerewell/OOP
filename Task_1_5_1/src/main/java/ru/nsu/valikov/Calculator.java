package ru.nsu.valikov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Calculator {
    Deque<String> predicats = new ArrayDeque<>();
    Deque<String> arguments = new ArrayDeque<>();
    List<String> expression = new ArrayList<>();

    //    public static void main(String[] args) {
    //        double a = 1e+5;// дабл не имеет оверфлоу, кек какой-то
    //        System.out.println(a);
    //    }

    private boolean isPredicat(String s) {
        Set<String> predicats = new HashSet<>() {
            {
                add("+");
                add("-");
                add("*");
                add("/");
                add("log");
                add("pow");
                add("sqrt");
                add("sin");
                add("cos");
            }
        };
        return predicats.contains(s);
    }

    private boolean isNumber(String s) {
        String pattern = "(\\d+|\\d+.\\d+|\\d*+\\d+i|\\d*-\\d+i|\\d+%)";
        String kek="\\d+(\\d)*|\\d*"
        return s.matches(pattern);
    }

    public void parser(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(file);
             InputStreamReader streamReader = new InputStreamReader(
                     Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedReader input = new BufferedReader(streamReader); Scanner scanner = new Scanner(
                input).useDelimiter("\\s")) { // only one whitespace char
            while (scanner.hasNext()) {
                String s = scanner.next();
                System.out.println(s);
                if (isPredicat(s)) {
                    predicats.addLast(s);
                } else if (isNumber(s)) {
                    arguments.addLast(s);
                } else {
                    throw new MissingFormatArgumentException("Wrong expression format!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(predicats);
        System.out.println(arguments);
    }
    // useDelimiter(");
    // комплексные в " "
    // грады с %
    // можно побитовыми операциями проверять оверфлоу
    // стек операций и стек чисел, счетчик от 0 до 2?  когда достигаем знака или функции типа
    // пов
    // лог, обнуляем счётчик, счетчик == 2 производим операцию с начала стека и поп фром стек,
    // счетчик ставим на 1.
    // храним распаршенный массив, и указатель на текущий элемент.
    //

    double plus(double numberA, double numberB) {
        //        Math.ad
        return numberA + numberB;
    }

    //        ComplexNumber plus(Record numberA, ComplexNumber numberB) {
    //
    //    }

    double minus(double numberA, double numberB) {
        return numberA - numberB;
    }

    double multiplication(double numberA, double numberB) {
        return numberA * numberB;
    }
    //    Ove
}
