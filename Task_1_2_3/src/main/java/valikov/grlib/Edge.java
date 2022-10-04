package valikov.grlib;
// nado
class Edge<T> extends CutEdge<T> {
    private Node<T> start;

    Edge(Integer weight, Node<T> end, Node<T> start) {
        super(weight, end);
        this.start=start;
    }
}
