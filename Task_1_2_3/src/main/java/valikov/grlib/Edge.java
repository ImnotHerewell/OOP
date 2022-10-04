package valikov.grlib;


// nado
class Edge<E, N> {
    private final E identifier;
    private Node<E, N> start;
    private Node<E, N> end;
    private Integer weight;

    Edge(E identifier, Node<E, N> start, Node<E, N> end, Integer weight) {
        this.identifier = identifier;
        this.start = start;
        this.end = end;
        this.weight = weight;
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
    public int hashCode() {
        return identifier.hashCode();
    }
}
