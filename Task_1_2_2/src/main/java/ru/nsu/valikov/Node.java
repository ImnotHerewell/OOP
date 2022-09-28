package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T value;
    private T parent;
    private List<Node<T>> children= new ArrayList<>();

    public void setValue (T value){
        this.value=value;
    }
    public T getValue(){
        return this.value;
    }
    public T getParent(T parent){
        return this.parent;
    }
    public void addChild(Node<T> child){
        this.children.add(child);
    }

    public void deleteChildren(){
        for (Node<T> child : children){
            child.deleteChildren();
        }
        this.children=null;
        this.setValue(null);
    }

    public List<Node<T>> getChildren(){
        return this.children;
    }

}
