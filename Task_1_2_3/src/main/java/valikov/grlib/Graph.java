package valikov.grlib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

// дженерики только для вершин, типа вершина может быть типа стринг дефолт или какой-то объект, с вэйлью не, хотя в теории да.
public class Graph<E, N> {
    private HashMap<N, Node<E, N>> mapOfAllNodes;// быстрее по complexity и проще, больше памяти требует
    private HashMap<E, Edge<E, N>> mapOfALlEdges; // костыль?

    public static void main(String[] args) {

    }

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

    public N addNode(N identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfAllNodes.containsKey(identifier)) {
            return identifier;
        }
        Node<E, N> newNode = new Node<>(identifier);
        mapOfAllNodes.put(identifier, newNode);
        return identifier;
    }

    public boolean removeNode(N identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (!mapOfAllNodes.containsKey(identifier)) {
            return false;
        }
        Node<E, N> node = mapOfAllNodes.get(identifier);
        mapOfAllNodes.remove(identifier);
        for (int indexEdge = 0; indexEdge < node.getListOfEdges().size(); indexEdge++) {
            Edge<E, N> edgeForDelete = node.getListOfEdges().get(indexEdge);
            edgeForDelete.delete();
        }
        return true;
    }

    public Node<E, N> getNode(N identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfAllNodes.containsKey(identifier)) {
            return mapOfAllNodes.get(identifier);
        }
        return null;
    }


    public E addEdge(E identifier, N start, N end, Integer weight) {
        if (identifier == null || start == null || end == null || weight == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (!mapOfAllNodes.containsKey(start) || !mapOfAllNodes.containsKey(end)) {
            throw new IllegalArgumentException("Some nodes are not created.");
        }
        if (mapOfALlEdges.containsKey(identifier)) {
            return identifier;
        }
        Node<E, N> startNode = mapOfAllNodes.get(start);
        Node<E, N> endNode = mapOfAllNodes.get(end);
        Edge<E, N> newEdge = new Edge<>(identifier, startNode, endNode, weight);
        startNode.addEdge(newEdge);
        mapOfALlEdges.put(identifier, newEdge);
        return identifier;
    }

    public boolean removeEdge(E identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (!mapOfALlEdges.containsKey(identifier)) {
            return false;
        }
        Edge<E, N> edge = mapOfALlEdges.get(identifier);
        mapOfALlEdges.remove(identifier);
        edge.getStart().getListOfEdges().remove(edge);
        edge.delete();
        return true;
    }

    public Edge<E, N> getEdge(E identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfALlEdges.containsKey(identifier)) {
            return mapOfALlEdges.get(identifier);
        }
        return null;
    }

    public boolean setNodeIdentifier(N identifier, N newIdentifier) {
        if (identifier == null || newIdentifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfAllNodes.containsKey(newIdentifier)) {
            return false;
        }
        if (!mapOfAllNodes.containsKey(identifier)) {
            addNode(newIdentifier);
        } else {
            mapOfAllNodes.get(identifier).setIdentifier(newIdentifier);
        }
        return true;
    }

    public N getNodeIdentifier(N identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        return mapOfAllNodes.get(identifier).getIdentifier();
    }

    public boolean setEdgeIdentifier(E identifier, E newIdentifier) {
        if (identifier == null || newIdentifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfALlEdges.containsKey(newIdentifier) || !mapOfALlEdges.containsKey(identifier)) {
            return false;
        }
        mapOfALlEdges.get(identifier).setIdentifier(newIdentifier);
        return true;
    }

    public E getEdgeIdentifier(E identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        return mapOfALlEdges.get(identifier).getIdentifier();
    }

    public int nodeCount() {
        return mapOfAllNodes.size();
    }

    public int edgeCount() {
        return mapOfALlEdges.size();
    }

    public List<NodeValue<Node<E, N>, Integer>> Dijkstra(N identifier) {
        for (Map.Entry<E, Edge<E, N>> hashEdge : mapOfALlEdges.entrySet()) {
            if (hashEdge.getValue().getWeight() < 0) {
                throw new UnsupportedOperationException("Edges with negative weights are not supported.");
            }
        }
        HashMap<Node<E, N>, Integer> mapWeights = new HashMap<>();
        for (Map.Entry<N, Node<E, N>> hashNode : mapOfAllNodes.entrySet()) {
            mapWeights.put(hashNode.getValue(), Integer.MAX_VALUE);
        }
        SortedSet<NodeValue<Node<E, N>, Integer>> set = new TreeSet<>();
        NodeValue<Node<E, N>, Integer> root = new NodeValue<>(getNode(identifier), 0);
        mapWeights.replace(root.getFirst(), 0);
        set.add(root);
        while (!set.isEmpty()) {
            Node<E, N> minNode = set.first().getFirst();
            set.remove(set.first());
            for (Edge<E, N> edge : minNode.getListOfEdges()) {
                Node<E, N> to = edge.getEnd();
                int weight = edge.getWeight();
                if (mapWeights.get(minNode) + weight < mapWeights.get(to)) {
                    NodeValue<Node<E, N>, Integer> toNodeValue = new NodeValue<>(to, weight);
                    set.remove(toNodeValue);
                    toNodeValue.setSecond(mapWeights.get(to));
                    mapWeights.replace(to, mapWeights.get(minNode) + weight);
                    set.add(toNodeValue);
                }
            }
        }
        List<NodeValue<Node<E, N>, Integer>> listRes = new ArrayList<>();
        for (Map.Entry<Node<E, N>, Integer> hashNode : mapWeights.entrySet()) {
            listRes.add(new NodeValue<>(hashNode.getKey(), hashNode.getValue()));
        }
        Collections.sort(listRes);
        return listRes;
    }
}
