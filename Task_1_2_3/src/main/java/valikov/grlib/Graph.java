package valikov.grlib;

import java.util.HashMap;

// дженерики только для вершин, типа вершина может быть типа стринг дефолт или какой-то объект, с вэйлью не, хотя в теории да.
public class Graph<E, N> {
    private Node<E, N> node;
    HashMap<N, Node<E, N>> mapOfAllNodes;// быстрее по complexity и проще, больше памяти требует
    private HashMap<Edge<E, N>, Edge<E, N>> mapOfALlEdges; // костыль?

    public void addNode(N identifier) throws IllegalArgumentException {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfAllNodes.containsKey(identifier))
            return;
        Node<E, N> newNode = new Node<>(identifier);
        mapOfAllNodes.put(identifier, newNode);
    }

    public void addEdge(E identifier, N start, N end, Integer weight) throws IllegalArgumentException{
        if (identifier == null || start == null || end == null || weight == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (!mapOfAllNodes.containsKey(start) || !mapOfAllNodes.containsKey(end)) {
            throw new IllegalArgumentException("Some nodes are not created.");
        }
        Node<E, N> startNode = mapOfAllNodes.get(start);
        Node<E, N> endNode = mapOfAllNodes.get(end);
        Edge<E, N> newEdge = new Edge<>(identifier, startNode, endNode, weight);
        startNode.addEdge(newEdge);
        mapOfALlEdges.put(newEdge, newEdge);
    }
}
