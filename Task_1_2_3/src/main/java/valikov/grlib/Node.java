package valikov.grlib;

import java.util.ArrayList;
import java.util.List;

public class Node<E, N> {
    private N identifier;
    private final List<Edge<E, N>> listOfEdges;

    Node(N identifier) {
        listOfEdges = new ArrayList<>();
        this.identifier = identifier;
    }

    void setIdentifier(N identifier) {
        this.identifier = identifier;
    }

    N getIdentifier() {
        return identifier;
    }

    List<Edge<E, N>> getListOfEdges() {
        return listOfEdges;
    }

    void addEdge(Edge<E, N> edge) {
        listOfEdges.add(edge);
    }

    void delete() {
        setIdentifier(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Node<E, N> node = (Node<E, N>) o;
        return identifier == node.identifier;
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

}
