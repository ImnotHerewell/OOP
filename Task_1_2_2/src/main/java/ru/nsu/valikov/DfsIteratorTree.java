package ru.nsu.valikov;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


/**
 * Iterator for DFS.
 *
 * @param <T> non-primitive type.
 */
public class DfsIteratorTree<T> implements Iterator<T> {
    private Node<T> current;
    private final Stack<Node<T>> stack = new Stack<>(); // Stack where nodes are stored.

    /**
     * Constructor for iterator, it adds adjacent with root nodes.
     *
     * @param tree our tree.
     */
    public DfsIteratorTree(Tree<T> tree) {
        current = tree.getRoot();
        for (Node<T> autoIt : tree.getRoot().getChildren()) {
            stack.push(autoIt);
        }
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return !stack.empty() || current.getChildCount() > 0;
    }

    /**
     * Returns the next element in the iteration. Method adds adjacent nodes if it needs.
     * And pop last element to current.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() throws NoSuchElementException {
        if (current.getValue() != null) {
            for (Node<T> autoIt : current.getChildren()) {
                stack.push(autoIt);
            }
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        current = stack.pop();
        return current.getValue();
    }

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.
     * The behavior of an iterator is unspecified if this method is called
     * after a call to the {@link #forEachRemaining forEachRemaining} method.
     */
    @Override
    public void remove() {
        current.delete();
    }

}
