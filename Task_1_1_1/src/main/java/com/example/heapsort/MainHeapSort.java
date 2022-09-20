package com.example.heapsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of Heap Sort in Java by Nikolay Valikov, @nocarend.
 */
public class MainHeapSort {
    /**
     * Just a main method to write an output.
     */
    public static void main() {
        ArrayList<Integer> mainList = new ArrayList<>(Arrays.asList(5, 34, 2, 432, 4, 2, 56, 765, 7, 65, 7, 65, 7, 5, 8, 56, 3, 7, 45, 7, 47));
        heapSort(mainList);
        System.out.print(mainList);
    }

    /**
     * Algorithm for building a heap.
     * If the indexI-th element is less
     * than its children, the entire subtree is already a heap.
     * Otherwise, we swap the indexI-th
     * element with the smallest of its children,
     * after which we perform siftDown for this son.
     *
     * @param currentList - list with numbers.
     * @param listSize    - size of currentList.
     * @param indexI      - index of element, which should be sifted down.
     */
    public static void siftDown(List<Integer> currentList, int listSize, int indexI) {
        int left;
        int right;
        int indexJ;
        while (2 * indexI + 1 < listSize) {
            left = 2 * indexI + 1;
            right = 2 * indexI + 2;
            indexJ = left;
            if (right < listSize && currentList.get(right) < currentList.get(left)) {
                indexJ = right;
            }
            if (currentList.get(indexI) <= currentList.get(indexJ)) {
                break;
            }
            Collections.swap(currentList, indexI, indexJ);
            indexI = indexJ;
        }
    }

    /**
     * Default heapsort algorithm.
     *
     * @param inputList - list with numbers.
     */
    public static void heapSort(List<Integer> inputList) {
        int curListSize = inputList.size();
        int mainListSize = curListSize;
        for (int indexI = mainListSize / 2 - 1; indexI >= 0; indexI--) { // building a heap
            siftDown(inputList, mainListSize, indexI);
        }
        for (int elemI = 0; elemI < mainListSize; elemI++) { // work with a built heap
            Collections.swap(inputList, 0, mainListSize - elemI - 1);
            curListSize--;
            siftDown(inputList, curListSize, 0);
        }
        Collections.reverse(inputList);
    }
}
