package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T value;
    private Node<T> parent;
    private List<Node<T>> children= new ArrayList<>();

    public void setValue (T value){
        this.value=value;
    }
    public T getValue(){
        return this.value;
    }
    public void setParent(Node<T> parent){
        this.parent=parent;
    }
    public Node<T> getParent(T parent){
        return this.parent;
    }
    public void addChild(Node<T> child){
        this.children.add(child);
    }

    public void delete(){
        for (Node<T> child : children){
            child.parent=this.parent;
        }
        this.parent.children.remove(this);
    }

    public List<Node<T>> getChildren(){
        return this.children;
    }

}
