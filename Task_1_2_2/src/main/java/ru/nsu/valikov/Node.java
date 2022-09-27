package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T value;
    List<Node<T>> children= new ArrayList<>();

    public T get(){
        return value;
    }

    public Node<T> add(T nodeInput){
        Node<T> newNode = new Node<>();
        newNode.value=nodeInput;
        this.children.add(newNode);
        return this;
    }

    public Node<T> add (Node<T> curNode, T childValue){
        Node<T> newNode = new Node<>();
        curNode.value=childValue;
        curNode.children.add(newNode);
        return newNode;
    }


}
