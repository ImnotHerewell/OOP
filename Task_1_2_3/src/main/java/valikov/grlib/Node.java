package valikov.grlib;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//nado
class Node<E, N> {
    private final N identifier;
    private List<Edge<E, N>> listOfEdges;

    Node(N identifier){
        listOfEdges =new ArrayList<>();
        this.identifier=identifier;
    }
    public void addEdge(Edge<E, N> edge){
        listOfEdges.add(edge);
    }
    public List<Edge<E, N>> getListOfEdges() {
        return listOfEdges;
    }

    public N getIdentifier() {
        return identifier;
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }
}
