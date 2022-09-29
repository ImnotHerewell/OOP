package ru.nsu.valikov;

import java.util.Iterator;

public class BFSIteratorTree<T> implements Iterator<T> {
    private Node<T> current;

    public BFSIteratorTree(Tree<T> tree) {
        this.current = tree.getRoot();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}
