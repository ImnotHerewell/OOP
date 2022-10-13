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
 * the objects are in some sense "related".
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

    /**
     * Constructor for adjacency and incidence matrices.
     *
     * @param edgeIdentifiers - list with edge identifier and its weight
     * @param nodeIdentifiers - list with node identifier
     * @param matrix          - adjacency or incidence matrix
     * @param type            if 0 - incidence else adjacency
     */
    public Graph(List<Pair<E, Integer>> edgeIdentifiers, List<N> nodeIdentifiers,
                 List<List<Integer>> matrix, Integer type) {
        if (nodeIdentifiers.size() != matrix.size()) {
            throw new UnsupportedOperationException("Quantity of nodes in matrix and"
                    + " in list of nodes must be equal.");
        }
        buildMap();
        Iterator<Pair<E, Integer>> it = null;
        if (type == 1) {
            it = edgeIdentifiers.listIterator();
        }
        for (int indexRow = 0; indexRow < matrix.size(); indexRow++) {
            for (int indexColumn = 0; indexColumn < matrix.get(indexRow).size();
                 indexColumn++) {
                Integer edgeCheck = matrix.get(indexRow).get(indexColumn);
                if (type == 1 && edgeCheck == 1) {
                    Pair<E, Integer> edgeWeight = it.next();
                    addEdge(edgeWeight.getFirst(), nodeIdentifiers.get(indexRow),
                            nodeIdentifiers.get(indexColumn), edgeWeight.getSecond());
                } else if (type == 0 && edgeCheck != Integer.MIN_VALUE) {
                    E edgeIdentifier = edgeIdentifiers.get(indexColumn).getFirst();
                    @SuppressWarnings("unchecked")
                    N nodeToIdentifier = (N) edgeIdentifiers.get(indexColumn).getSecond();
                    Integer weight = matrix.get(indexRow).get(indexColumn);
                    N nodeStartIdentifier = nodeIdentifiers.get(indexRow);
                    addEdge(edgeIdentifier, nodeStartIdentifier, nodeToIdentifier, weight);
                }
            }
        }
    }

    /**
     * Constructor of graph from adjacency list.
     * <Triple<N, E, Integer>>>
     * N - node identifier,
     * E - edge identifier,
     * Integer - node weight.
     *
     * @param adjacencyList list with nodes,
     *                      and it's triple of adjacency node identifier,
     *                      edge identifier and weight
     */
    public Graph(List<NodeAndListOfAdjacencyEdges<N, List<Triple<N, E, Integer>>>> adjacencyList) {
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


    public HashMap<N, Node<E, N>> getMapOfAllNodes() {
        return mapOfAllNodes;
    }

    public HashMap<E, Edge<E, N>> getMapOfAllEdges() {
        return mapOfAllEdges;
    }

    /**
     * Add node to graph.
     *
     * @param identifier node's identifier
     * @return node's identifier if identifier doesn't exist
     */
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

    /**
     * Remove node from graph.
     *
     * @param identifier of node which should be removed
     * @return graph if node exists
     */
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

    /**
     * Set new identifier for node.
     *
     * @param identifier    old identifier
     * @param newIdentifier new identifier
     * @return graph if success
     */
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

    /**
     * Get node's identifier, lol.
     *
     * @param identifier node's identifier
     * @return identifier if exists
     */
    public N getNodeIdentifier(N identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        return mapOfAllNodes.get(identifier).getIdentifier();
    }

    /**
     * Get node from graph.
     *
     * @param identifier node's identifier
     * @return node if identifier exists else null.
     */
    public Node<E, N> getNode(N identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        if (mapOfAllNodes.containsKey(identifier)) {
            return mapOfAllNodes.get(identifier);
        }
        return null;
    }


    /**
     * Add new edge to graph.
     *
     * @param identifier edge's identifier
     * @param start      identifier of edge's begin
     * @param end        identifier of edge's end
     * @param weight     of edge
     * @return edge's identifier if success
     */
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

    /**
     * Remove an edge from graph.
     *
     * @param identifier edge's identifier
     * @return true if edge exists
     */
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

    /**
     * Set new identifier for edge.
     *
     * @param identifier    old identifier
     * @param newIdentifier new identifier
     * @return true if success
     */
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

    /**
     * Get edge's identifier.
     *
     * @param identifier edge's identifier
     * @return identifier if exists
     */
    public E getEdgeIdentifier(E identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Null pointers are not supported.");
        }
        return mapOfAllEdges.get(identifier).getIdentifier();
    }

    /**
     * Get edge from graph.
     *
     * @param identifier edge's identifier
     * @return edge if identifier exists
     */
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

    /**
     * Dijkstra algorithm for searching minimal paths to other nodes.
     *
     * @param identifier node's identifier, where algorithm starts working
     * @return list with node and it's minimal weights path
     */
    public List<NodeValue<Node<E, N>>> dijkstra(N identifier) {
        for (Map.Entry<E, Edge<E, N>> hashEdge : mapOfAllEdges.entrySet()) {
            if (hashEdge.getValue().getWeight() < 0) {
                throw new UnsupportedOperationException("Edges with negative weights"
                        + " are not supported.");
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
        return mapOfAllNodes.equals(graph.mapOfAllNodes)
                && mapOfAllEdges.equals(graph.mapOfAllEdges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapOfAllNodes, mapOfAllEdges);
    }
}
