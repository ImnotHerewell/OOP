package ru.nsu.valikov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import java.util.MissingFormatArgumentException;
import java.util.Objects;
import java.util.Scanner;

public class Calculator {
    private final Deque<Pair> arguments = new ArrayDeque<>();
    private final List<String> expressions = new ArrayList<>();

    //    public static void main(String[] args) {
    //        double a = 1e+5;// дабл не имеет оверфлоу, кек какой-то
    //        System.out.println(a);
    //    }
    private boolean isPlus(String s) {
        return s.equals("+");
    }

    private boolean isMinus(String s) {
        return s.equals("-");
    }

    private boolean isMult(String s) {
        return s.equals("*");
    }

    private boolean isDiv(String s) {
        return s.equals("/");
    }

    private boolean isLog(String s) {
        return s.equals("log");
    }

    private boolean isPow(String s) {
        return s.equals("pow");
    }

    private boolean isSqrt(String s) {
        return s.equals("sqrt");
    }

    private boolean isSin(String s) {
        return s.equals("sin");
    }

    private boolean isCos(String s) {
        return s.equals("cos");
    }

    private boolean isSinglePredicat(String s) {
        return isSin(s) || isCos(s) || isSqrt(s);
    }

    private boolean isDoublePredicat(String s) {
        return isPlus(s) || isMinus(s) || isMult(s) || isDiv(s) || isLog(s) || isPow(s);
    }

    private boolean isPredicat(String s) {
        return isSinglePredicat(s) || isDoublePredicat(s);
    }

    private boolean isDouble(String s) {
        String pattern = "(\\d+)(.\\d+)?";//double
        return s.matches(pattern);
    }

    private boolean isComplex(String s) {
        String pattern = "([-]?\\d+(.\\d+)?)?([-|+](\\d+(.\\d+)?))?[i$]";// complex num, вроде
        return s.matches(pattern);
    }

    private boolean isDegree(String s) {
        String pattern = "(\\d+)(.\\d+)?%";//degrees
        return s.matches(pattern);
    }

    private boolean isNumber(String s) {
        return isComplex(s) || isDegree(s) || isDouble(s);
    }

    private void addParseElem(String s) {
        if (isDouble(s)) {
            arguments.addLast(new Pair(new ComplexNumber(Double.parseDouble(s), 0), 0));
        } else if (isComplex(s)) {
            if (s.matches("([-]?\\d+(.\\d+)?)([-|+](\\d+(.\\d+)?))[i$]")) {
                s = s.replace("i", "");
                s = s.replace("-", " -");
                s = s.replace("+", " ");
                Scanner scan = new Scanner(s).useLocale(Locale.US);
                arguments.addLast(
                        new Pair(new ComplexNumber(scan.nextDouble(), scan.nextDouble()), 0));
                //                                                                             .getSecond());
            } else if (s.matches("[-]?\\d+(.\\d+)?[$]")) {
                arguments.addLast(new Pair(new ComplexNumber(Double.parseDouble(s), 0), 0));
            } else if (s.matches("([-]?(\\d+(.\\d+)?))[i$]")) {
                s = s.replace("i", "");
                arguments.addLast(new Pair(new ComplexNumber(0, Double.parseDouble(s)), 0));
            }
        } else if (isDegree(s)) {
            arguments.addLast(new Pair(new Degree(Double.parseDouble(s.replace("%", ""))), 1));
        }
    }

    private void detectFunction(String function) {
        if (function.matches("[+]|[-]|[*]|[/]|pow|log")) {

        } else if (function.matches("sin|cos|sqrt")) {
            singleFunctionExecution(function);
        }
    }

    private void singleFunctionExecution(String function) {
        if (isSin(function)) {
            Pair arg=arguments.peekLast();
//            arg.a().
        }
    }

    private void doubleFunctionExecution(String function) {
        if (arguments.size() < 2) {
            throw new MissingFormatArgumentException("Wrong arguments format!");
        }
        Pair firstArg = arguments.pop();
        Pair secondArg = arguments.peekLast();
        if (isPlus(function)) {
            Objects.requireNonNull(secondArg).a().plus(firstArg);
        } else if (isMinus(function)) {
            Objects.requireNonNull(secondArg).a().minus(firstArg);
        }
    }

    // храним распаршенный массив, и указатель на текущий элемент.
    //
    //    private void numberExe(int counter) {
    //        if (predicats.size() > 0) {
    //            String predicat = predicats.getLast();
    //            if (counter == 1 && isSinglePredicat(predicat)) {
    //
    //            }
    //        }
    //    }

    private Pair calculation() {
        int pointer = expressions.size() - 1;
        String parseElem;
        while (pointer >= 0) {
            parseElem = expressions.get(pointer--);
            if (isNumber(parseElem)) {
                addParseElem(parseElem);
                //                System.out.println(
                //                        arguments.peekLast().a().getValue() + "+" + arguments
                //                        .peekLast().a()
                //                                                                             .getSecond());
            } else if (isPredicat(parseElem)) {
                if (isSinglePredicat(parseElem)){
                    singleFunctionExecution(parseElem);
                }
                else if (isDoublePredicat(parseElem)){
                    doubleFunctionExecution(parseElem);
                }
            }
        }
        if (arguments.size()!=1){
            throw new MissingFormatArgumentException("Wrong format!");
        }
        return arguments.pop();
    }

    public Expr parser(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(file);
             InputStreamReader streamReader = new InputStreamReader(
                     Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedReader input = new BufferedReader(streamReader); Scanner scanner = new Scanner(
                input).useDelimiter("\\s+")) {
            while (scanner.hasNext()) {
                String s = scanner.next();
                //                                System.out.println(s);
                if (isPredicat(s) || isNumber(s)) {
                    expressions.add(s);
                } else {
                    throw new MissingFormatArgumentException("Wrong expression format!");
                }
            }
            Pair res=calculation();
            return res.a();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
