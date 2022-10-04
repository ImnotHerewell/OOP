package valikov.grlib;

import java.util.HashMap;
import java.util.List;

// дженерики только для вершин, типа вершина может быть типа стринг дефолт или какой-то объект, с вэйлью не, хотя в теории да.
public class Graph<E, N> {
    HashMap<N, Node<E, N>> mapOfAllNodes;// быстрее по complexity и проще, больше памяти требует
    private HashMap<E, Edge<E, N>> mapOfALlEdges; // костыль?

    public Graph(List<N> nodeList) {
        mapOfAllNodes = new HashMap<>();
        mapOfALlEdges = new HashMap<>();
        for (N identifier : nodeList) {
            mapOfAllNodes.put(identifier, new Node<E, N>(identifier));
        }
    }

    public HashMap<N, Node<E, N>> getMapOfAllNodes() {
        return mapOfAllNodes;
    }

    public HashMap<E, Edge<E, N>> getMapOfALlEdges() {
        return mapOfALlEdges;
    }

    public N addNode(N identifier) throws IllegalArgumentException {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfAllNodes.containsKey(identifier))
            return identifier;
        Node<E, N> newNode = new Node<>(identifier);
        mapOfAllNodes.put(identifier, newNode);
        return identifier;
    }

    public Node<E, N> getNode(N identifier) throws IllegalArgumentException {
        if (identifier == null) {
            throw new IllegalArgumentException();
        }
        if (mapOfAllNodes.containsKey(identifier)) {
            return mapOfAllNodes.get(identifier);
        }
        return null;
    }

    public E addEdge(E identifier, N start, N end, Integer weight) throws IllegalArgumentException {
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
        mapOfALlEdges.put(identifier, newEdge);
        return identifier;
    }

    public Edge<E, N> getEdge(E identifier) throws IllegalArgumentException {
        if (identifier == null) {
            throw new IllegalArgumentException();
        }
        if (mapOfALlEdges.containsKey(identifier)) {
            return mapOfALlEdges.get(identifier);
        }
        return null;
    }
}
