package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @param <T> non-primitive type.
 */
public class Node<T> {
    private T value;
    private Node<T> parent;
    private Integer childCount;
    private final List<Node<T>> children;

    public Node() {
        childCount = 0;
        children = new ArrayList<>();
        parent = null;
        value = null;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void addChild(Node<T> child) {
        if (child != null) {
            childCount++;
            children.add(child);
        }
    }

    public void delete() {
        if (value != null) {
            for (Node<T> child : children) {
                child.parent = parent;
                parent.addChild(child);
            }
            parent.childCount--;
            parent.children.remove(this);
        }
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public int hashCode() {
        int p = 239017;
        int res = Objects.hashCode(value) + Objects.hashCode(parent.value) * p;
        for (Node<T> obj : children) {
            res += obj.hashCode();
        }
        return res;
    }
}
