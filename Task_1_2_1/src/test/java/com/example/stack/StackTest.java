package com.example.stack;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
class MainStackTest {

    /**
     * Simple test on push.
     */
    @Test
    void pushTest() {
        MainStack<String> testStack = new MainStack<>();
        String test = "baobab";
        testStack.push(test);
        String[] expectedResult = new String[MainStack.MAX_SIZE];
        expectedResult[0] = "baobab";
        Assertions.assertArrayEquals(expectedResult, testStack.array);
    }

    /**
     * Simple test on pop.
     */
    @Test
    void popTest() {
        MainStack<String> testStack = new MainStack<>();
        testStack.push("baobab");
        final String expectedResult = "baobab";
        String test = testStack.pop();
        Assertions.assertEquals(expectedResult, test);
    }

    /**
     * Simple test on pushStack.
     */
    @Test
    void pushStackTest() {
        MainStack<String> testStack = new MainStack<>();
        String[] inputValues = new String[]{"a", "b", "c", "d", "e"};
        testStack.pushStack(inputValues);
        String[] expectedResult = new String[MainStack.MAX_SIZE];
        expectedResult[0] = "a";
        expectedResult[1] = "b";
        expectedResult[2] = "c";
        expectedResult[3] = "d";
        expectedResult[4] = "e";
        Assertions.assertArrayEquals(expectedResult, testStack.array);
    }

    /**
     * Simple test on popStack.
     */
    @Test
    void popStackTest() {
        MainStack<String> testStack = new MainStack<>();
        String[] inputValues = new String[]{"a", "b", "c", "d", "e"};
        testStack.pushStack(inputValues);
        String[] expectedResult = new String[MainStack.MAX_SIZE];
        expectedResult[0] = "b";
        expectedResult[1] = "c";
        expectedResult[2] = "d";
        expectedResult[3] = "e";
        MainStack<String> testResult = testStack.popStack(4);
        Assertions.assertArrayEquals(expectedResult, testResult.array);
    }

    /**
     * Simple test on count.
     */
    @Test
    void countTest() {
        MainStack<String> testStack = new MainStack<>();
        String[] inputValues = new String[]{"a", "b", "c", "d", "e"};
        testStack.pushStack(inputValues);
        int expectedResult = 5;
        int testResult = testStack.count();
        Assertions.assertEquals(expectedResult, testResult);
    }
}
