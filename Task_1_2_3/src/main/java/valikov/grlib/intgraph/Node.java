package valikov.grlib.intgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class for Graph.
 *
 * @param <E> edge's identifier.
 * @param <N> node's identifier.
 */
public class Node<E, N> {
    private N identifier;
    private final List<Edge<E, N>> listOfEdges;

    public Node(N identifier) {
        listOfEdges = new ArrayList<>();
        this.identifier = identifier;
    }

    public void setIdentifier(N identifier) {
        this.identifier = identifier;
    }

    public N getIdentifier() {
        return identifier;
    }

    public List<Edge<E, N>> getListOfEdges() {
        return listOfEdges;
    }

    public void addEdge(Edge<E, N> edge) {
        listOfEdges.add(edge);
    }

    public void delete() {
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
