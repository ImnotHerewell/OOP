package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of tree.
 *
 * @param <T> non-primitive type.
 * @author Valikov Nikolay, nocarend.
 */
public class Tree<T extends Comparable<T>> implements Iterable<T> {
    private Integer size;
    private final Node<T> root; // it's imagine node.

    public Tree() {
        root = new Node<>();
        size=0;
    }

    public Integer getSize() {
        return size;
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
        size++;
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
                size--;
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

    public List<Node<T>> toList() {
        List<Node<T>> list = new ArrayList<>();
        for (T value : this) {
            Node<T> newNode = new Node<>();
            newNode.setValue(value);
            list.add(newNode);
        }
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        List<Node<T>> listThis = toList();
        @SuppressWarnings("unchecked")
        Tree<T> obj = (Tree<T>) o;
        List<Node<T>> listObj = obj.toList();
        listThis.sort(Comparator.comparing(Node::getValue));
        listObj.sort(Comparator.comparing(Node::getValue));
        return listObj.equals(listThis);
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
