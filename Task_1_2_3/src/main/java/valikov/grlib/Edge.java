package valikov.grlib;

import java.util.Objects;

// nado
class Edge<E, N> extends CutEdge<E, N> {
    private Node<E, N> start;

    Edge(Integer weight, Node<E, N> end, Node<E, N> start, E identifier) {
        super(weight, end, identifier);
        this.start = start;
    }

    public void setStart(Node<E, N> start) {
        this.start = start;
    }

    public Node<E, N> getStart() {
        return start;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier());
    }
}
