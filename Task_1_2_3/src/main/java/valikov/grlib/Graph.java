package valikov.grlib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Graph is a structure amounting to a set of objects in which some pairs of
 * the objects are in some sense "related"
 *
 * @param <E> Edge's object (identifier)
 * @param <N> Node's object (identifier)
 */
public class Graph<E, N> {
    private HashMap<N, Node<E, N>> mapOfAllNodes;
    private HashMap<E, Edge<E, N>> mapOfAllEdges;

    public Graph() {
        buildMap();
    }

    public Graph(Pair<List<E>, List<N>> edgeNodeIdentifiers,
                 List<List<Integer>> adjacencyMatrix) {
        if (edgeNodeIdentifiers.getSecond().size() != adjacencyMatrix.size()) {
            throw new UnsupportedOperationException("Quantity of nodes in matrix and" +
                    " in list of nodes must be equal.");
        }
        int matrixEdgeCount = 0;
        for (List<Integer> list : adjacencyMatrix) {
            if (list.size() != adjacencyMatrix.size()) {
                throw new UnsupportedOperationException("Rows and columns must be equal.");
            }
            for (Integer weight : list) {
                if (weight != Integer.MIN_VALUE) {
                    matrixEdgeCount++;
                }
            }
        }
        edgeCheck(matrixEdgeCount, edgeNodeIdentifiers.getFirst().size());
        buildMap();
        Iterator<E> it = edgeNodeIdentifiers.getFirst().listIterator();
        for (int indexRow = 0; indexRow < adjacencyMatrix.size(); indexRow++) {
            for (int indexColumn = 0; indexColumn < adjacencyMatrix.get(indexRow).size();
                 indexColumn++) {
                Integer weight = adjacencyMatrix.get(indexRow).get(indexColumn);
                if (weight != Integer.MIN_VALUE) {
                    addEdge(it.next(), edgeNodeIdentifiers.getSecond().get(indexRow),
                            edgeNodeIdentifiers.getSecond().get(indexColumn), weight);
                }
            }
        }
    }

    public Graph(List<N> nodeIdentifiers, List<Pair<E, N>> edgeAndNodeList,
                 List<List<Integer>> incidenceMatrix) {
        if (nodeIdentifiers.size() != incidenceMatrix.size()) {
            throw new UnsupportedOperationException("Quantity of nodes in matrix and" +
                    " in list of nodes must be equal.");
        }
        int matrixEdgeCount = 0;
        for (List<Integer> list : incidenceMatrix) {
            if (list.size() != edgeAndNodeList.size()) {
                throw new UnsupportedOperationException("Wrong matrix.");
            }
            for (Integer weight : list) {
                if (weight != Integer.MIN_VALUE) {
                    matrixEdgeCount++;
                }
            }
        }
        System.out.println(incidenceMatrix);
        edgeCheck(matrixEdgeCount, edgeAndNodeList.size());
        buildMap();
        for (int indexRow = 0; indexRow < incidenceMatrix.size(); indexRow++) {
            for (int indexColumn = 0; indexColumn < incidenceMatrix.get(indexRow).size();
                 indexColumn++) {
                Integer weight = incidenceMatrix.get(indexRow).get(indexColumn);
                if (weight != Integer.MIN_VALUE) {
                    addEdge(edgeAndNodeList.get(indexColumn).getFirst(),
                            nodeIdentifiers.get(indexRow),
                            edgeAndNodeList.get(indexColumn).getSecond(), weight);
                }
            }
        }
    }


    public Graph(List<Pair<N, List<Triple<N, E, Integer>>>> adjacencyList) {
        buildMap();
        for (Pair<N, List<Triple<N, E, Integer>>> nodeEdge : adjacencyList) {
            N startIdentifier = nodeEdge.getFirst();
            for (Triple<N, E, Integer> edge : nodeEdge.getSecond()) {
                E edgeIdentifier = edge.getSecond();
                N nodeIdentifier = edge.getFirst();
                Integer weight = edge.getThird();
                addEdge(edgeIdentifier, startIdentifier, nodeIdentifier, weight);
            }
        }
    }


    private void buildMap() {
        this.mapOfAllNodes = new HashMap<>();
        this.mapOfAllEdges = new HashMap<>();
    }

    private void edgeCheck(int edgeCount, int identifierCount) {
        if (edgeCount != identifierCount) {
            throw new UnsupportedOperationException("Wrong matrix.");
        }
    }

    public HashMap<N, Node<E, N>> getMapOfAllNodes() {
        return mapOfAllNodes;
    }

    public HashMap<E, Edge<E, N>> getMapOfAllEdges() {
        return mapOfAllEdges;
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

    public Graph<E, N> removeNode(N identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (!mapOfAllNodes.containsKey(identifier)) {
            throw new IllegalArgumentException("Graph does not contain given node's identifier.");
        }
        Node<E, N> node = mapOfAllNodes.get(identifier);
        mapOfAllNodes.remove(identifier);
        for (int indexEdge = 0; indexEdge < node.getListOfEdges().size(); indexEdge++) {
            Edge<E, N> edgeForDelete = node.getListOfEdges().get(indexEdge);
            mapOfAllEdges.remove(edgeForDelete.getIdentifier());
        }
        return this;
    }

    public Graph<E, N> setNodeIdentifier(N identifier, N newIdentifier) {
        if (identifier == null || newIdentifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfAllNodes.containsKey(newIdentifier)) {
            throw new IllegalArgumentException("New identifier is not available.");
        }
        if (!mapOfAllNodes.containsKey(identifier)) {
            addNode(newIdentifier);
        } else {
            Node<E, N> node = mapOfAllNodes.get(identifier);
            node.setIdentifier(newIdentifier);
            mapOfAllNodes.remove(identifier);
            mapOfAllNodes.put(newIdentifier, node);
        }
        return this;
    }

    public N getNodeIdentifier(N identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        return mapOfAllNodes.get(identifier).getIdentifier();
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
        if (mapOfAllEdges.containsKey(identifier)) {
            return identifier;
        }
        addNode(start);
        addNode(end);
        Node<E, N> startNode = mapOfAllNodes.get(start);
        Node<E, N> endNode = mapOfAllNodes.get(end);
        Edge<E, N> newEdge = new Edge<>(identifier, startNode, endNode, weight);
        startNode.addEdge(newEdge);
        mapOfAllEdges.put(identifier, newEdge);
        return identifier;
    }

    public boolean removeEdge(E identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (!mapOfAllEdges.containsKey(identifier)) {
            return false;
        }
        Edge<E, N> edge = mapOfAllEdges.get(identifier);
        mapOfAllEdges.remove(identifier);
        edge.getStart().getListOfEdges().remove(edge);
        return true;
    }

    public boolean setEdgeIdentifier(E identifier, E newIdentifier) {
        if (identifier == null || newIdentifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfAllEdges.containsKey(newIdentifier) || !mapOfAllEdges.containsKey(identifier)) {
            return false;
        }
        Edge<E, N> edge = mapOfAllEdges.get(identifier);
        mapOfAllEdges.remove(identifier);
        edge.setIdentifier(newIdentifier);
        mapOfAllEdges.put(newIdentifier, edge);
        return true;
    }

    public E getEdgeIdentifier(E identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        return mapOfAllEdges.get(identifier).getIdentifier();
    }

    public Edge<E, N> getEdge(E identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfAllEdges.containsKey(identifier)) {
            return mapOfAllEdges.get(identifier);
        }
        return null;
    }

    public int nodeCount() {
        return mapOfAllNodes.size();
    }

    public int edgeCount() {
        return mapOfAllEdges.size();
    }

    public List<NodeValue<Node<E, N>>> dijkstra(N identifier) {
        for (Map.Entry<E, Edge<E, N>> hashEdge : mapOfAllEdges.entrySet()) {
            if (hashEdge.getValue().getWeight() < 0) {
                throw new UnsupportedOperationException("Edges with negative weights" +
                        " are not supported.");
            }
        }
        HashMap<Node<E, N>, Integer> mapWeights = new HashMap<>();
        for (Map.Entry<N, Node<E, N>> hashNode : mapOfAllNodes.entrySet()) {
            mapWeights.put(hashNode.getValue(), Integer.MAX_VALUE);
        }
        SortedSet<NodeValue<Node<E, N>>> set = new TreeSet<>();
        NodeValue<Node<E, N>> root = new NodeValue<>(getNode(identifier), 0);
        mapWeights.replace(root.getFirst(), 0);
        set.add(root);
        while (!set.isEmpty()) {
            Node<E, N> minNode = set.first().getFirst();
            set.remove(set.first());
            for (Edge<E, N> edge : minNode.getListOfEdges()) {
                Node<E, N> to = edge.getEnd();
                int weight = edge.getWeight();
                if (mapWeights.get(minNode) + weight < mapWeights.get(to)) {
                    NodeValue<Node<E, N>> toNodeValue =
                            new NodeValue<>(to, mapWeights.get(to));
                    set.remove(toNodeValue);
                    toNodeValue.setSecond(mapWeights.get(minNode) + weight);
                    mapWeights.replace(to, toNodeValue.getSecond());
                    set.add(toNodeValue);
                }
            }
        }
        List<NodeValue<Node<E, N>>> listRes = new ArrayList<>();
        for (Map.Entry<Node<E, N>, Integer> hashNode : mapWeights.entrySet()) {
            listRes.add(new NodeValue<>(hashNode.getKey(), hashNode.getValue()));
        }
        Collections.sort(listRes);
        return listRes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked") Graph<E, N> graph = (Graph<E, N>) o;
        return mapOfAllNodes.equals(graph.mapOfAllNodes) &&
                mapOfAllEdges.equals(graph.mapOfAllEdges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapOfAllNodes, mapOfAllEdges);
    }
}
