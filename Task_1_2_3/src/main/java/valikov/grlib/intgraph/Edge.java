package valikov.grlib.intgraph;


/**
 * Edge class for graph.
 *
 * @param <E> edge's identifier.
 * @param <N> node's identifier.
 */
public class Edge<E, N> {
    private E identifier;
    private Node<E, N> start;
    private Node<E, N> end;
    private Integer weight;

    /**
     * Default constructor for creating edge.
     *
     * @param identifier edge's identifier.
     * @param start node.
     * @param end node.
     * @param weight number parameter for edge.
     */
    public Edge(E identifier, Node<E, N> start, Node<E, N> end, Integer weight) {
        this.identifier = identifier;
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public void setIdentifier(E identifier) {
        this.identifier = identifier;
    }

    public E getIdentifier() {
        return identifier;
    }

    public void setStart(Node<E, N> start) {
        this.start = start;
    }

    public Node<E, N> getStart() {
        return start;
    }

    public void setEnd(Node<E, N> newEnd) {
        end = newEnd;
    }

    public Node<E, N> getEnd() {
        return end;
    }

    public void setWeight(Integer newWeight) {
        weight = newWeight;
    }

    public Integer getWeight() {
        return weight;
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
        Edge<E, N> edge = (Edge<E, N>) o;
        return identifier.equals(edge.identifier)
                && start.equals(edge.start) && end.equals(edge.end)
                && weight.equals(edge.weight);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }
}
