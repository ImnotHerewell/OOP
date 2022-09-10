<<<<<<< HEAD
package com.example.stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MainStackTest {

  @Test
  void pushTest() {
    MainStack<String> testStack = new MainStack<>();
    String test = "baobab";
    testStack.push(test);
    String[] expectedResult = new String[MainStack.MAX_SIZE];
    expectedResult[0] = "baobab";
    assertArrayEquals(expectedResult, testStack.array);
  }

  @Test
  void popTest() {
    MainStack<String> testStack = new MainStack<>();
    testStack.push("baobab");
    String expectedResult = "baobab";
    String test = testStack.pop();
    assertEquals(expectedResult, test);
  }

  @Test
  void pushStackTest() {
    MainStack<String> testStack = new MainStack<>();
    String[] inputValues = new String[] {"a", "b", "c", "d", "e"};
    testStack.pushStack(inputValues);
    String[] expectedResult = new String[MainStack.MAX_SIZE];
    expectedResult[0] = "a";
    expectedResult[1] = "b";
    expectedResult[2] = "c";
    expectedResult[3] = "d";
    expectedResult[4] = "e";
    assertArrayEquals(expectedResult, testStack.array);
  }

  @Test
  void popStackTest() {
    MainStack<String> testStack = new MainStack<>();
    String[] inputValues = new String[] {"a", "b", "c", "d", "e"};
    testStack.pushStack(inputValues);
    MainStack<String> testResult = testStack.popStack(4);
    String[] expectedResult = new String[MainStack.MAX_SIZE];
    expectedResult[0] = "b";
    expectedResult[1] = "c";
    expectedResult[2] = "d";
    expectedResult[3] = "e";
    assertArrayEquals(expectedResult, testResult.array);
  }

  @Test
  void countTest() {
    MainStack<String> testStack = new MainStack<>();
    String[] inputValues = new String[] {"a", "b", "c", "d", "e"};
    testStack.pushStack(inputValues);
    int expectedResult = 5;
    int testResult = testStack.count();
    assertEquals(expectedResult, testResult);
  }
}
=======
package com.example.stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MainStackTest {

  @Test
  void pushTest() {
    MainStack<String> testStack = new MainStack<>();
    String test = "baobab";
    testStack.push(test);
    String[] expectedResult = new String[MainStack.MAX_SIZE];
    expectedResult[0] = "baobab";
    assertArrayEquals(expectedResult, testStack.array);
  }

  @Test
  void popTest() {
    MainStack<String> testStack = new MainStack<>();
    testStack.push("baobab");
    String expectedResult = "baobab";
    String test = testStack.pop();
    assertEquals(expectedResult, test);
  }

  @Test
  void pushStackTest() {
    MainStack<String> testStack = new MainStack<>();
    String[] inputValues = new String[] {"a", "b", "c", "d", "e"};
    testStack.pushStack(inputValues);
    String[] expectedResult = new String[MainStack.MAX_SIZE];
    expectedResult[0] = "a";
    expectedResult[1] = "b";
    expectedResult[2] = "c";
    expectedResult[3] = "d";
    expectedResult[4] = "e";
    assertArrayEquals(expectedResult, testStack.array);
  }

  @Test
  void popStackTest() {
    MainStack<String> testStack = new MainStack<>();
    String[] inputValues = new String[] {"a", "b", "c", "d", "e"};
    testStack.pushStack(inputValues);
    MainStack<String> testResult = testStack.popStack(4);
    String[] expectedResult = new String[MainStack.MAX_SIZE];
    expectedResult[0] = "b";
    expectedResult[1] = "c";
    expectedResult[2] = "d";
    expectedResult[3] = "e";
    assertArrayEquals(expectedResult, testResult.array);
  }

  @Test
  void countTest() {
    MainStack<String> testStack = new MainStack<>();
    String[] inputValues = new String[] {"a", "b", "c", "d", "e"};
    testStack.pushStack(inputValues);
    int expectedResult = 5;
    int testResult = testStack.count();
    assertEquals(expectedResult, testResult);
  }
}
>>>>>>> 73d719c (fun:)
