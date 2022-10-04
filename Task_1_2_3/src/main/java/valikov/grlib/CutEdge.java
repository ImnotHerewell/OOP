package valikov.grlib;

class CutEdge<E, N> {
    private Integer weight;
    private Node<E, N> end;
    private final E identifier;

    CutEdge(Integer weight, Node<E, N> end, E identifier) {
        this.weight = weight;
        this.end = end;
        this.identifier = identifier;
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

    public E getIdentifier() {
        return identifier;
    }
}
