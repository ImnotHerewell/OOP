package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Tree's cell.
 *
 * @param <T> non-primitive type.
 */
public class Node<T> {
    private T value;
    private Node<T> parent; // exists only for delete function.
    private Integer childCount; // exists only for iterator.hasNext().
    private final List<Node<T>> children;

    /**
     * Default constructor.
     */
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

    public Integer getChildCount() {
        return childCount;
    }

    /**
     * Simple adding a child.
     *
     * @param child to whom edge goes.
     */
    public void addChild(Node<T> child) {
        if (child != null) {
            childCount++;
            children.add(child);
        }
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    /**
     * Delete a current node and link children to node's parent.
     */
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


    /**
     * Checks objects on equality.
     *
     * @param o object.
     * @return true if objects are equal, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked")
        Node<T> node = (Node<T>) o;
        return Objects.equals(value, node.value) && Objects.equals(parent, node.parent) && Objects.equals(childCount, node.childCount) && Objects.equals(children, node.children);
    }

    /**
     * Calculates tree's hashCode.
     *
     * @return hash.
     */
    @Override
    public int hashCode() {
        int p = 239017;
        int res = Objects.hashCode(value) + Objects.hashCode(parent.value) * p;
        for (Node<T> obj : children) {
            res += obj.hashCode();
        }
        return res;
    }
}
