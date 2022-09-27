package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tree<T> implements Iterable<T> {
    public static void main(String[] args) {
        System.out.println("Hello, Tree!");
    }

    private T nodeValue;
    List<Tree<T>> children= new ArrayList<>();

    public T get(){
        return nodeValue;
    }

    public Tree<T> add(T nodeInput){
        Tree<T> newNode = new Tree<T>();
        newNode.nodeValue=nodeInput;
        this.children.add(newNode);
        return this;
    }

    public Tree<T> add (Tree<T> curNode, T childValue){
        Tree<T> newNode = new Tree<T>();
        curNode.nodeValue=childValue;
        curNode.children.add(newNode);
        return newNode;
    }

    public void remove ()

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
