package com.example.stack;

/**
 * Implementation of Stack in Java by Nikolay Valikov, @nocarend.
 *
 * @param <T> - any Object.
 */
public class MainStack<T> {
  int curSize = 0;
  static final int MAX_SIZE = 10000;
  public T[] array = (T[]) new Object[MAX_SIZE];

  /**
   * @param inputElem - Object for push.
   * @return - checking that we push or not.
   */
  boolean push(T inputElem) {
    if (curSize == MAX_SIZE) return false;
    this.array[curSize++] = inputElem;
    return true;
  }

  /**
   * @return - checking that stack empty or not.
   */
  public T pop() {
    if (curSize == 0) return null;
    return this.array[--curSize];
  }

  /**
   * @param inputArray - array that should be pushed.
   * @return - it is possible or not.
   */
  public boolean pushStack(T[] inputArray) {
    if (inputArray.length + curSize >= MAX_SIZE) return false;
    for (T Elem : inputArray) {
      this.push(Elem);
    }
    return true;
  }

  /**
   * @param quantityOfElems - how many elements we want to extract from stack.
   * @return - null - impossible, notnull - possible.
   */
  public MainStack<T> popStack(int quantityOfElems) {
    MainStack<T> newStack = new MainStack<T>();
    if (quantityOfElems > curSize) return null;
    else {
      for (int indexArray = curSize - quantityOfElems; indexArray < curSize; indexArray++) {
        newStack.push(this.array[indexArray]);
      }
      for (int quantityI = 0; quantityI < quantityOfElems; quantityI++) {
        this.pop();
      }
      return newStack;
    }
  }

  /**
   * @return - how many elements in stack.
   */
  public int count() {
    return curSize;
  }
}
