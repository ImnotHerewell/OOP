package ru.nsu.valikov;

import java.util.Iterator;

public class Tree<T> implements Iterable<T> {
    private Node<T> root;

    public Tree() {
        this.root = new Node<>();
    }

    public static void main(String[] args) {
        Tree<String> kek = new Tree<>();
        Node<String> node2 = kek.add("B");
        Node<String> node3 = kek.add("A");
        Node<String> node1 = kek.add("C");
        kek.add(node2, "D");
        Node<String> node4 = kek.add(node2, "F");
        kek.add(node4, "E");
        kek.add(node4, "R");
        kek.add(node4, "O");
        kek.add(node4, "T");
        Iterator<String> it=kek.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }
        System.out.println("Hello, Tree!");
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


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new DFSIteratorTree<>(this);
    }

//    public Iterator<T> iteratorBFS() {
//        return new BFSIteratorTree<>(this);
//    }
}
