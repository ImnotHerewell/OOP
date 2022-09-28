package com.example.stack;

/**
 * Implementation of Stack in Java by Nikolay Valikov, @author nocarend.
 *
 * @param <T> - any Object.
 */
public class Stack<T> {
    static final int MAX_SIZE = 10000;
    @SuppressWarnings("unchecked")
    public T[] array = (T[]) new Object[MAX_SIZE];
    int curSize = 0;
    java.util.Stack<String> stack;
    /**
     * Default push in.
     *
     * @param inputElem - Object for push.
     * @return - checking that we push or not.
     */
    boolean push(T inputElem) {
        if (curSize == MAX_SIZE) {
            return false;
        }
        this.array[curSize++] = inputElem;
        return true;
    }

    /**
     * Default pop out.
     *
     * @return - checking that stack empty or not.
     */
    public T pop() {
        if (curSize == 0) {
            return null;
        }
        return this.array[--curSize];
    }

    /**
     * Push elements from array in stack.
     *
     * @param inputArray - array that should be pushed.
     * @return - it is possible or not.
     */
    public boolean pushStack(T[] inputArray) {
        if (inputArray.length + curSize >= MAX_SIZE) {
            return false;
        }
        for (T elem : inputArray) {
            this.push(elem);
        }
        return true;
    }

    /**
     * Pop quantityOfElems elements from stack.
     *
     * @param quantityOfElems - how many elements we want to extract from stack.
     * @return - null - impossible, notnull - possible.
     */
    public Stack<T> popStack(int quantityOfElems) {
        Stack<T> newStack = new Stack<>();
        if (quantityOfElems > curSize) {
            return null;
        } else {
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
     * How many elements are in stack.
     *
     * @return - how many elements in stack.
     */
    public int count() {
        return curSize;
    }

    /**
     * Hello, Stack.
     *
     * @param args ...
     */
    public static void main(String[] args) {
        System.out.println("Hello, Stack!");
    }
}
