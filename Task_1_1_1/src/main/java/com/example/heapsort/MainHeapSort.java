package com.example.heapsort;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of Heap Sort in Java by Nikolay Valikov, @nocarend.
 */
public class MainHeapSort {

    /**
     * @param currentList - list with numbers.
     * @param listSize    - size of currentList.
     * @param indexI      - index of element, which should be sifted down.
     *                    If the indexI-th element is less
     *                    than its children, the entire subtree is already a heap.
     *                    Otherwise, we swap the indexI-th
     *                    element with the smallest of its children,
     *                    after which we perform siftDown for this son.
     */
    public static void siftDown(List<Integer> currentList, int listSize, int indexI) {
        int left, right, indexJ;
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
