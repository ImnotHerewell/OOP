package valikov.grlib;

import java.util.HashMap;

// дженерики только для вершин, типа вершина может быть типа стринг дефолт или какой-то объект, с вэйлью не, хотя в теории да.
public class Graph<E, N> {
    private Node<E, N> node;
    HashMap<N, Node<E, N>> mapOfAllNodes;
    private HashMap<Edge<E, N>, Edge<E, N>> setOfALlEdges; // быстрее по complexity и проще, больше памяти требует

    public void addNode(N identifier) throws NullPointerException {
        if (identifier == null) {
            throw new NullPointerException();
        }
        if (mapOfAllNodes.containsKey(identifier))
            return;
        Node<E, N> newNode = new Node<>(identifier);
        mapOfAllNodes.put(identifier,newNode);
    }
}
