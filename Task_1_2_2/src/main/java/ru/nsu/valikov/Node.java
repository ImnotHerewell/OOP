package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<T> {
    private T value;
    private Node<T> parent;
    private Integer childCount;
    private final List<Node<T>> children;

    public Node() {
        this.childCount = 0;
        this.children = new ArrayList<>();
        this.parent = null;
        this.value = null;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return this.parent;
    }

    public Integer getChildCount() {
        return this.childCount;
    }

    public void addChild(Node<T> child) {
        if (child != null) {
            this.childCount++;
            this.children.add(child);
        }
    }

    public void delete() {
        if (this.value != null) {
            for (Node<T> child : this.children) {
                child.parent = this.parent;
                this.parent.addChild(child);
            }
            this.parent.childCount--;
            this.parent.children.remove(this);
        }
    }

    public List<Node<T>> getChildren() {
        return this.children;
    }

    public int hashCode(){
        int p =239017;
        int res= Objects.hashCode(this.value)+Objects.hashCode(getParent().value)*p;
        for (Node<T> obj : this.getChildren()){
            res+=obj.hashCode();
        }
        return res;
    }
}
