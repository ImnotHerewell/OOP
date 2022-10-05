package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Tree's cell.
 *
 * @param <T> non-primitive type.
 */
public class Node<T extends Comparable<T>> implements Comparable<T> {
    private T value;
    private Node<T> parent; // exists only for delete function.
    private final List<Node<T>> children;

    /**
     * Default constructor.
     */
    public Node() {
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
        return children.size();
    }

    /**
     * Simple adding a child.
     *
     * @param child to whom edge goes.
     */
    public void addChild(Node<T> child) {
        if (child != null) {
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Node<T> node = (Node<T>) o;
        return node.value == this.value;
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

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(T o) {
        return value.compareTo(o);
    }
}
