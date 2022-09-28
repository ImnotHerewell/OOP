package ru.nsu.valikov;

import java.util.Iterator;

public class Tree<T> implements Iterable<T> {
    private Node<T> root;

    public Tree() {
        root=new Node<>();
        root.setValue(null);
    }

    public static void main(String[] args) {
        Tree <String> kek=new Tree<>();
        Tree <String> tet=new Tree<>();
        kek.add("B");
        kek.add("A");
        Node<T> node1 = kek.add("C");
        for (String string :kek){
            System.out.println(string);
        }
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
        return new DFSIteratorTree<T>(this);
    }
}
