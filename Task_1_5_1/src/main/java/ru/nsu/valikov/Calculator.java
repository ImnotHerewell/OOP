package ru.nsu.valikov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import java.util.MissingFormatArgumentException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main class, reads data, do checks, calculate from others.
 */
public class Calculator {
    private final Deque<Pair> arguments = new ArrayDeque<>(); // numeric data
    private final List<String> expressions = new ArrayList<>(); // list with parse data

    private static boolean isPlus(String s) {
        return s.equals("+");
    }

    private static boolean isMinus(String s) {
        return s.equals("-");
    }

    private static boolean isMultiplication(String s) {
        return s.equals("*");
    }

    private static boolean isDiv(String s) {
        return s.equals("/");
    }

    private static boolean isLog(String s) {
        return s.equals("log");
    }

    private static boolean isPow(String s) {
        return s.equals("pow");
    }

    private static boolean isSqrt(String s) {
        return s.equals("sqrt");
    }

    private static boolean isSin(String s) {
        return s.equals("sin");
    }

    private static boolean isCos(String s) {
        return s.equals("cos");
    }

    private static boolean isSingleFunction(String s) {
        return isSin(s) || isCos(s) || isSqrt(s) || isLog(s);
    }

    private static boolean isDoubleFunction(String s) {
        return isPlus(s) || isMinus(s) || isMultiplication(s) || isDiv(s) || isPow(s);
    }

    private static boolean isFunction(String s) {
        return isSingleFunction(s) || isDoubleFunction(s);
    }

    /**
     * Is data double or not.
     *
     * @param s unparse data.
     * @return true if it is double else false.
     */
    private static boolean isDouble(String s) {
        String pattern = "-?\\d+(.\\d+)?";
        return s.matches(pattern);
    }

    /**
     * Is data complex number or not.
     *
     * @param s unparse data.
     * @return true if it is complex number else false.
     */
    private static boolean isComplex(String s) {
        String pattern = "(-?\\d+(.\\d+)?)?([-|+](\\d+(.\\d+)?))?[i$]";
        return s.matches(pattern);
    }

    /**
     * Is data degree or not.
     *
     * @param s unparse data.
     * @return true if it is degree else false.
     */
    private static boolean isDegree(String s) {
        String pattern = "(\\d+)(.\\d+)?%";
        return s.matches(pattern);
    }

    private static boolean isNumber(String s) {
        return isComplex(s) || isDegree(s) || isDouble(s);
    }

    /**
     * Makes result to normal output value.
     *
     * @param res result from calculation().
     * @return normal output value.
     */
    private static String outputFormat(Pair res) {
        if (res.b().equals(DataType.Degree)) {
            return BigDecimal.valueOf(res.a().getValue()).doubleValue() + "%";
        }
        if (res.a().getSecond() == 0) {
            return String.valueOf(BigDecimal.valueOf(res.a().getValue()).doubleValue());
        }
        String f = res.a().getSecond() < 0 ? "" : "+";
        return BigDecimal.valueOf(res.a().getValue()).doubleValue() + f + BigDecimal.valueOf(
                res.a().getSecond()).doubleValue() + "i";
    }

    /**
     * Adding parse element to expressions list.
     *
     * @param s unparse element.
     */
    private void addParseElem(String s) {
        if (isDouble(s)) {
            arguments.addLast(
                    new Pair(new ComplexNumber(Double.parseDouble(s), 0), DataType.Complex));
        } else if (isComplex(s)) {
            if (s.matches("(-?\\d+(.\\d+)?)([-|+](\\d+(.\\d+)?))[i$]")) {
                /*
                  For simplifying process.
                 */
                s = s.replace("i", "");
                s = s.replace("-", " -");
                s = s.replace("+", " ");
                Scanner scan = new Scanner(s).useLocale(Locale.US);
                arguments.addLast(new Pair(new ComplexNumber(scan.nextDouble(), scan.nextDouble()),
                        DataType.Complex));
            } else if (s.matches("(-?(\\d+(.\\d+)?))[i$]")) {
                s = s.replace("i", "");
                arguments.addLast(
                        new Pair(new ComplexNumber(0, Double.parseDouble(s)), DataType.Complex));
            }
        } else if (isDegree(s)) {
            arguments.addLast(
                    new Pair(new Degree(Double.parseDouble(s.replace("%", ""))), DataType.Degree));
        }
    }

    /**
     * Execute single function like log, sqrt, sin, cos.
     *
     * @param function function's name.
     */
    private void singleFunctionExecution(String function) {
        Pair arg = Objects.requireNonNull(arguments.pollLast());
        if (isSin(function)) {
            arg = new Pair(Objects.requireNonNull(arg).a().sin(), DataType.Complex);
        } else if (isCos(function)) {
            arg = new Pair(Objects.requireNonNull(arg).a().cos(), DataType.Complex);
        } else if (isLog(function)) {
            Objects.requireNonNull(arg).a().log();
        } else if (isSqrt(function)) {
            Objects.requireNonNull(arg).a().sqrt();
        }
        arguments.addLast(arg);
    }

    /**
     * Execute double function like pow, +, -, *, /.
     *
     * @param function function's name.
     */
    private void doubleFunctionExecution(String function) {
        Pair firstArg = Objects.requireNonNull(arguments.pollLast());
        Pair secondArg = Objects.requireNonNull(arguments.pollLast());
        if (isPlus(function)) {
            firstArg.a().plus(secondArg);
        } else if (isMinus(function)) {
            Objects.requireNonNull(firstArg).a().minus(secondArg);
        } else if (isMultiplication(function)) {
            Objects.requireNonNull(firstArg).a().multiplication(secondArg);
        } else if (isDiv(function)) {
            Objects.requireNonNull(firstArg).a().division(secondArg);
        } else if (isPow(function)) {
            Objects.requireNonNull(firstArg).a().pow(secondArg);
        }
        arguments.addLast(firstArg);
    }

    /**
     * Default algorithm for polish notation.
     *
     * @return result.
     */
    private Pair calculation() {
        int pointer = expressions.size() - 1;
        String parseElem;
        while (pointer >= 0) {
            parseElem = expressions.get(pointer--);
            if (isNumber(parseElem)) {
                addParseElem(parseElem);
            } else if (isFunction(parseElem)) {
                if (isSingleFunction(parseElem)) {
                    singleFunctionExecution(parseElem);
                } else if (isDoubleFunction(parseElem)) {
                    doubleFunctionExecution(parseElem);
                }
            }
        }
        if (arguments.size() != 1) {
            throw new MissingFormatArgumentException("Wrong format!");
        }
        return arguments.pop();
    }

    /**
     * Reads data from file.
     * File data example:
     * 1) + - * 2.13 -315 7 sin 30%
     *
     * @param file file's name.
     * @return user's output.
     */
    public String parser(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(file);
             InputStreamReader streamReader = new InputStreamReader(
                     Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedReader input = new BufferedReader(streamReader);
             Scanner scanner = new Scanner(input).useDelimiter("\\s+")) {
            while (scanner.hasNext()) {
                String s = scanner.next();
                if (isFunction(s) || isNumber(s)) {
                    expressions.add(s);
                } else {
                    throw new MissingFormatArgumentException("Wrong expression format!");
                }
            }
            return outputFormat(calculation());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("What are you doing, bro?");
    }
}
