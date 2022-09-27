package ru.nsu.valikov;

import java.util.Iterator;

public class Tree<T> implements Iterable<T> {
    private Node<T> root;

    public static void main(String[] args) {
        System.out.println("Hello, Tree!");
    }

    public Node<T> getRoot() {
        return root;
    }

    public Node<T> add(Node<T> curNode, T childValue) {
        Node<T> newNode = new Node<>();
        newNode.setValue(childValue);
        curNode.addChild(newNode);
        return newNode;
    }

    public Node<T> add(T childValue) {
        return add(root, childValue);
    }

//    public boolean remove(T value) {
//        Node<T> curNode = root;
//        while (true) {
//            if (curNode.getValue().equals(value)) {
//                curNode.deleteChildren();
//                return true;
//            }
//            for (Node<T> child : curNode.getChildren()) {
//                if ()
//            }
//        }
//    }

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
