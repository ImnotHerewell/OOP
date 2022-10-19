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
import valikov.grlib.records.EdgeTo;
import valikov.grlib.records.NodeValue;
import valikov.grlib.representation.AdjacencyList;
import valikov.grlib.representation.Matrix;

/**
 * Graph is a structure amounting to a set of objects in which some pairs of
 * the objects are in some sense "related".
 *
 * @param <E> Edge's object (identifier)
 * @param <N> Node's object (identifier)
 */
public class Graph<E, N> {
    private Map<N, Node<E, N>> mapOfAllNodes;
    private Map<E, Edge<E, N>> mapOfAllEdges;

    public Graph() {
        buildMap();
    }

    public Graph(EdgesAndTos<E, N> edges, List<N> nodes,
                 Matrix matrix, Integer type) {
        if (nodes.size() != matrix.matrixRowCount()) {
            throw new UnsupportedOperationException();
        }
        buildMap();
        Iterator<EdgeTo<E, N>> edgeIterator = type == 1 ? edges.list.iterator() : null;
        for (int indexRow = 0; indexRow < matrix.matrixRowCount(); indexRow++) {
            for (int indexColumn = 0; indexColumn < matrix.matrixColumnCount();
                 indexColumn++) {
                addInMatrix(matrix, indexRow, indexColumn, nodes, edges, type, edgeIterator);
            }
        }
    }

    public Graph(AdjacencyList<E, N> adjacencyList) {
        buildMap();
        for (var nodeEdges : adjacencyList.getMap().keySet()) {
            for (var edge : adjacencyList.getMap().get(nodeEdges).getAdjacencyNodes()) {
                E edgeIdentifier = edge.edge();
                N nodeIdentifier = edge.node();
                Integer weight = edge.weight();
                addEdge(edgeIdentifier, nodeEdges, nodeIdentifier, weight);
            }
        }
    }


    private void buildMap() {
        this.mapOfAllNodes = new HashMap<>();
        this.mapOfAllEdges = new HashMap<>();
    }

    private void addInMatrix(Matrix matrix, Integer indexRow, Integer indexColumn,
                             List<N> nodes, EdgesAndTos<E, N> edges, Integer type,
                             Iterator<EdgeTo<E, N>> edgeIterator) {
        Integer edgeCheck = matrix.get(indexRow, indexColumn);
        N nodeToIdentifier;
        N nodeStartIdentifier = nodes.get(indexRow);
        if (type == 1 && edgeCheck == 1) {
            EdgeTo<E, N> edgeTo = edgeIterator.next();
            nodeToIdentifier = nodes.get(indexColumn);
            addEdge(edgeTo.edge(), nodeStartIdentifier,
                    nodeToIdentifier, (Integer) edgeTo.to());
        } else if (type == 0 && edgeCheck != Integer.MIN_VALUE) {
            E edgeIdentifier = edges.get(indexColumn).edge();
            nodeToIdentifier = edges.get(indexColumn).to();
            addEdge(edgeIdentifier, nodeStartIdentifier, nodeToIdentifier, edgeCheck);
        }
    }

    private void dijkstraInit(Map<Node<E, N>, Integer> mapWeights,
                              SortedSet<NodeValue<N>> set,
                              NodeValue<N> root) {
        for (var hashNode : mapOfAllNodes.entrySet()) {
            mapWeights.put(hashNode.getValue(), Integer.MAX_VALUE);
        }
        mapWeights.replace(mapOfAllNodes.get(root.first()), 0);
        set.add(root);
    }

    private void dijkstraStep(Node<E, N> minNode,
                              Map<Node<E, N>, Integer> mapWeights,
                              SortedSet<NodeValue<N>> set) {
        set.remove(set.first());
        for (var edge : minNode.getListOfEdges()) {
            Node<E, N> to = edge.getEnd();
            int weight = edge.getWeight();
            if (mapWeights.get(minNode) + weight < mapWeights.get(to)) {
                NodeValue<N> toNodeValue =
                        new NodeValue<>(to.getIdentifier(), mapWeights.get(to));
                set.remove(toNodeValue);
                toNodeValue = new NodeValue<>(toNodeValue.first(), mapWeights.get(minNode) + weight);
                mapWeights.replace(to, toNodeValue.second());
                set.add(toNodeValue);
            }
        }
    }


    public Map<N, Node<E, N>> getMapOfAllNodes() {
        return mapOfAllNodes;
    }

    public Map<E, Edge<E, N>> getMapOfAllEdges() {
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
    public List<NodeValue<N>> dijkstraAlgo(N identifier) {
        for (var hashEdge : mapOfAllEdges.entrySet()) {
            if (hashEdge.getValue().getWeight() < 0) {
                throw new UnsupportedOperationException("Edges with negative weights"
                        + " are not supported.");
            }
        }
        Map<Node<E, N>, Integer> mapWeights = new HashMap<>();
        SortedSet<NodeValue<N>> set = new TreeSet<>();
        NodeValue<N> root = new NodeValue<>(getNode(identifier).getIdentifier(), 0);
        dijkstraInit(mapWeights, set, root);
        while (!set.isEmpty()) {
            Node<E, N> minNode = mapOfAllNodes.get(set.first().first());
            dijkstraStep(minNode, mapWeights, set);
        }
        List<NodeValue<N>> listRes = new ArrayList<>();
        for (var hashNode : mapWeights.entrySet()) {
            listRes.add(new NodeValue<>(hashNode.getKey().getIdentifier(), hashNode.getValue()));
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