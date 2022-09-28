package com.example.stack;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
class StackTest {

    /**
     * Simple test on push.
     */
    @Test
    void pushTest() {
        Stack<String> testStack = new Stack<>();
        String test = "baobab";
        testStack.push(test);
        String[] expectedResult = new String[Stack.MAX_SIZE];
        expectedResult[0] = "baobab";
        Assertions.assertArrayEquals(expectedResult, testStack.array);
    }

    /**
     * Simple test on pop.
     */
    @Test
    void popTest() {
        Stack<String> testStack = new Stack<>();
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
        Stack<String> testStack = new Stack<>();
        String[] inputValues = new String[]{"a", "b", "c", "d", "e"};
        testStack.pushStack(inputValues);
        String[] expectedResult = new String[Stack.MAX_SIZE];
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
        Stack<String> testStack = new Stack<>();
        String[] inputValues = new String[]{"a", "b", "c", "d", "e"};
        testStack.pushStack(inputValues);
        String[] expectedResult = new String[Stack.MAX_SIZE];
        expectedResult[0] = "b";
        expectedResult[1] = "c";
        expectedResult[2] = "d";
        expectedResult[3] = "e";
        Stack<String> testResult = testStack.popStack(4);
        Assertions.assertArrayEquals(expectedResult, testResult.array);
    }

    /**
     * Simple test on count.
     */
    @Test
    void countTest() {
        Stack<String> testStack = new Stack<>();
        String[] inputValues = new String[]{"a", "b", "c", "d", "e"};
        testStack.pushStack(inputValues);
        int expectedResult = 5;
        int testResult = testStack.count();
        Assertions.assertEquals(expectedResult, testResult);
    }
}
