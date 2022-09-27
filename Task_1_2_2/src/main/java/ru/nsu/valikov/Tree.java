package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tree<T> implements Iterable<T> {
    Node<T> root;
    public static void main(String[] args) {
        System.out.println("Hello, Tree!");
    }

    public Node<T> getRoot(){
        return root;
    }


//    public void remove ()

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorTree<T>(this);
    }
}
