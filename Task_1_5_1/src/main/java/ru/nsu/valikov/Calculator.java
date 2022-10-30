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
        return isSin(s) || isCos(s) || isSqrt(s) || isLog(s);
    }

    private boolean isDoublePredicat(String s) {
        return isPlus(s) || isMinus(s) || isMult(s) || isDiv(s) || isPow(s);
    }

    private boolean isPredicat(String s) {
        return isSinglePredicat(s) || isDoublePredicat(s);
    }

    private boolean isDouble(String s) {
        String pattern = "[-]?(\\d+)(.\\d+)?";
        return s.matches(pattern);
    }

    private boolean isComplex(String s) {
        String pattern = "([-]?\\d+(.\\d+)?)?([-|+](\\d+(.\\d+)?))?[i$]";
        return s.matches(pattern);
    }

    private boolean isDegree(String s) {
        String pattern = "(\\d+)(.\\d+)?%";
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
            } else if (s.matches("([-]?(\\d+(.\\d+)?))[i$]")) {
                s = s.replace("i", "");
                arguments.addLast(new Pair(new ComplexNumber(0, Double.parseDouble(s)), 0));
            }
        } else if (isDegree(s)) {
            arguments.addLast(new Pair(new Degree(Double.parseDouble(s.replace("%", ""))), 1));
        }
    }

    private void singleFunctionExecution(String function) {
        if (arguments.size() < 1) {
            throw new MissingFormatArgumentException("Wrong arguments format!");
        }
        Pair arg = arguments.pollLast();
        if (isSin(function)) {
            arg = new Pair(Objects.requireNonNull(arg).a().sin(), 0);
        } else if (isCos(function)) {
            arg = new Pair(Objects.requireNonNull(arg).a().cos(), 0);
        } else if (isLog(function)) {
            Objects.requireNonNull(arg).a().log();
        } else if (isSqrt(function)) {
            Objects.requireNonNull(arg).a().sqrt();
        }
        arguments.addLast(arg);
    }

    private void doubleFunctionExecution(String function) {
        if (arguments.size() < 2) {
            throw new MissingFormatArgumentException("Wrong arguments format!");
        }
        Pair firstArg = arguments.pollLast();
        Pair secondArg = arguments.pollLast();
        if (isPlus(function)) {
            Objects.requireNonNull(firstArg).a().plus(secondArg);
        } else if (isMinus(function)) {
            Objects.requireNonNull(firstArg).a().minus(secondArg);
        } else if (isMult(function)) {
            Objects.requireNonNull(firstArg).a().multiplication(secondArg);
        } else if (isDiv(function)) {
            Objects.requireNonNull(firstArg).a().division(secondArg);
        } else if (isPow(function)) {
            try {
                Objects.requireNonNull(firstArg).a().pow(secondArg);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        arguments.addLast(firstArg);
    }

    private Pair calculation() {
        int pointer = expressions.size() - 1;
        String parseElem;
        while (pointer >= 0) {
            parseElem = expressions.get(pointer--);
            if (isNumber(parseElem)) {
                addParseElem(parseElem);
            } else if (isPredicat(parseElem)) {
                if (isSinglePredicat(parseElem)) {
                    singleFunctionExecution(parseElem);
                } else if (isDoublePredicat(parseElem)) {
                    doubleFunctionExecution(parseElem);
                }
            }
        }
        if (arguments.size() != 1) {
            throw new MissingFormatArgumentException("Wrong format!");
        }
        return arguments.pop();
    }

    private String outputFormat(Pair res) {
        if (res.b() == 1) {
            return res.a().getValue() + "%";
        }
        if (res.a().getSecond() == 0) {
            return String.valueOf(res.a().getValue());
        }
        String f = res.a().getSecond() < 0 ? "" : "+";
        return res.a().getValue() + f + res.a().getSecond();
    }

    public String parser(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(file);
             InputStreamReader streamReader = new InputStreamReader(
                     Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedReader input = new BufferedReader(streamReader); Scanner scanner = new Scanner(
                input).useDelimiter("\\s+")) {
            while (scanner.hasNext()) {
                String s = scanner.next();
                if (isPredicat(s) || isNumber(s)) {
                    expressions.add(s);
                } else {
                    throw new MissingFormatArgumentException("Wrong expression format!");
                }
            }
            return outputFormat(calculation());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
