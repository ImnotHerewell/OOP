package ru.nsu.valikov;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Tree<T> implements Iterable<T> {
    private final Node<T> root;

    public Tree() {
        this.root = new Node<>();
    }


    public Node<T> getRoot() {
        return root;
    }

    public Node<T> add(Node<T> curNode, T childValue) {
        Node<T> newNode = new Node<>();
        newNode.setValue(childValue);
        newNode.setParent(curNode);
        curNode.addChild(newNode);
        return newNode;
    }

    public Node<T> add(T childValue) {
        return add(root, childValue);
    }

    public void erase(T value) {
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                iterator.remove();
                return;
            }
        }
//        throw new NoSuchElementException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new DFSIteratorTree<>(this);
    }

    public Iterator<T> iteratorBFS() {
        return new BFSIteratorTree<>(this);
    }
    public int hashCode(){
        int res=0;
        for (Node<T> obj : root.getChildren()){
            res+= obj.hashCode();
        }
        return res;
    }
}
