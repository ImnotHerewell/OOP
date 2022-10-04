package ru.nsu.valikov;

import java.util.Iterator;

/**
 * Implementation of tree.
 *
 * @param <T> non-primitive type.
 * @author Valikov Nikolay, nocarend.
 */
public class Tree<T> implements Iterable<T> {
    private final Node<T> root; // it's imagine node.

    public Tree() {
        root = new Node<>();
    }


    public Node<T> getRoot() {
        return root;
    }

    /**
     * Link newNode with childValue to curNode.
     *
     * @param curNode    currentNode.
     * @param childValue value that 'll be linked.
     * @return a newNode with childValue inside.
     */
    public Node<T> add(Node<T> curNode, T childValue) {
        Node<T> newNode = new Node<>();
        newNode.setValue(childValue);
        newNode.setParent(curNode);
        curNode.addChild(newNode);
        return newNode;
    }

    /**
     * Link a new node to root.
     *
     * @param childValue value that 'be linked.
     * @return a new node with childValue inside.
     */
    public Node<T> add(T childValue) {
        return add(root, childValue);
    }

    /**
     * Using DFSIterator to remove element.
     *
     * @param value 'll removed.
     */
    public void erase(T value) {
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                iterator.remove();
                return;
            }
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator for DFS.
     */
    @Override
    public Iterator<T> iterator() {
        return new DfsIteratorTree<>(this);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator for BFS.
     */
    public Iterator<T> iteratorBfs() {
        return new BfsIteratorTree<>(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked")
        Tree<T> tree = (Tree<T>) o;
        return o.hashCode() == this.hashCode();
    }

    /**
     * Trying to calculate tree's hash.
     *
     * @return hash.
     */

    @Override
    public int hashCode() {
        int res = 0;
        for (Node<T> obj : root.getChildren()) {
            res += obj.hashCode();
        }
        return res;
    }
}
