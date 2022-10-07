package valikov.grlib;


// nado
class Edge<E, N> {
    private E identifier;
    private Node<E, N> start;
    private Node<E, N> end;
    private Integer weight;

    Edge(E identifier, Node<E, N> start, Node<E, N> end, Integer weight) {
        this.identifier = identifier;
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    void setIdentifier(E identifier) {
        this.identifier = identifier;
    }

    E getIdentifier() {
        return identifier;
    }

    void setStart(Node<E, N> start) {
        this.start = start;
    }

    Node<E, N> getStart() {
        return start;
    }

    void setEnd(Node<E, N> newEnd) {
        end = newEnd;
    }

    Node<E, N> getEnd() {
        return end;
    }

    void setWeight(Integer newWeight) {
        weight = newWeight;
    }

    Integer getWeight() {
        return weight;
    }
    void delete(){
        setIdentifier(null);
        setStart(null);
        setEnd(null);
        setWeight(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked")
        Edge<E, N> edge = (Edge<E, N>) o;
        return identifier.equals(edge.identifier) && start.equals(edge.start) && end.equals(edge.end) && weight.equals(edge.weight);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }
}
