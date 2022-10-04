package valikov.grlib;

class CutEdge<T> {
    private Integer weight;
    private Node<T> end;

    CutEdge(Integer weight, Node<T> end) {
        this.weight = weight;
        this.end = end;
    }

    public Integer getWeight() {
        return weight;
    }

    public Node<T> getEnd() {
        return end;
    }

    public void setEnd(Node<T> newEnd) {
        end = newEnd;
    }

    public void setWeight(Integer newWeight) {
        weight = newWeight;
    }

}
