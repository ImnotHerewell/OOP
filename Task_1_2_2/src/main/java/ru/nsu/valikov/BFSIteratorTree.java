package ru.nsu.valikov;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BFSIteratorTree<T> implements Iterator<T> {
    private Node<T> current;
    private final Queue<Node<T>> queue = new LinkedList<>();

    public BFSIteratorTree(Tree<T> tree) {
        this.current = tree.getRoot();
        this.queue.addAll(tree.getRoot().getChildren());
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
        return !this.queue.isEmpty() || this.current.getChildCount() > 0;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() throws NoSuchElementException {
        if (this.current.getValue() != null) {
            this.queue.addAll(this.current.getChildren());
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.current = queue.remove();
        return this.current.getValue();
    }

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.
     * <p>
     * The behavior of an iterator is unspecified if this method is called
     * after a call to the {@link #forEachRemaining forEachRemaining} method.
     */
    @Override
    public void remove() {
        this.current.delete();
    }

}
