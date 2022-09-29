package ru.nsu.valikov;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class DFSIteratorTree<T> implements Iterator<T> {
    private Node<T> current;
    private final Stack<Node<T>> dfsStack = new Stack<>();

    public DFSIteratorTree(Tree<T> tree) {
        this.current = tree.getRoot();
        for (Node<T> autoIt : tree.getRoot().getChildren()) {
            this.dfsStack.push(autoIt);
        }
    }

    @Override
    public boolean hasNext() {
        return !this.dfsStack.empty() || this.current.getChildCount() > 0;
    }

    @Override
    public T next() throws NoSuchElementException {
        if (this.current.getValue() != null) {
            for (Node<T> autoIt : this.current.getChildren()) {
                this.dfsStack.push(autoIt);
            }
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.current = dfsStack.pop();
        return this.current.getValue();
    }

    @Override
    public void remove() {
        this.current.delete();
    }

}
