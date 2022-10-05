package ru.nsu.valikov;

import java.util.*;

/**
 * Iterator for BFS.
 *
 * @param <T> non-primitive type.
 */
public class BfsIteratorTree<T extends Comparable<T>> implements Iterator<T> {
    private Tree<T> tree;
    private final int modCount;
    private Node<T> current;
    private final Queue<Node<T>> queue = new LinkedList<>(); // Queue where nodes are stored.

    /**
     * Constructor for iterator, it adds adjacent with root nodes.
     *
     * @param tree our tree.
     */
    public BfsIteratorTree(Tree<T> tree) {
        this.tree = tree;
        modCount = 0;
        current = tree.getRoot();
        queue.addAll(tree.getRoot().getChildren());
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
        if (tree.getSize() != modCount) {
            throw new ConcurrentModificationException();
        }
        return !queue.isEmpty() || current.getChildCount() > 0;
    }

    /**
     * Returns the next element in the iteration.Method adds adjacent nodes if it needs.
     * And remove first element to current.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() throws NoSuchElementException {
        if (current.getValue() != null) {
            queue.addAll(current.getChildren());
        }
        if (tree.getSize() != modCount) {
            throw new ConcurrentModificationException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        current = queue.remove();
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
