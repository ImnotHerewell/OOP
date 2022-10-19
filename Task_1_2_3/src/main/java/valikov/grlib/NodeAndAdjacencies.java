package valikov.grlib;

import java.util.ArrayList;
import java.util.List;

/**
 * Node and its adjacency nodes.
 *
 * @param <E> edge's identifier type.
 * @param <N> node's identifier type.
 */
public class NodeAndAdjacencies<E, N> {
    private final N currentNode;
    private final List<NodeEdgeWeight<E, N>> adjacencyNodes;

    NodeAndAdjacencies(N currentNode) {
        this.currentNode = currentNode;
        adjacencyNodes = new ArrayList<>();
    }

    public List<NodeEdgeWeight<E, N>> getAdjacencyNodes() {
        return adjacencyNodes;
    }

    public void add(NodeEdgeWeight<E, N> noew) {
        adjacencyNodes.add(noew);
    }
}

/**
 * Record for adjacency node, its edge and edge's weight.
 *
 * @param node   node's identifier.
 * @param edge   edge's identifier.
 * @param weight edge's weight.
 * @param <E>    edge's identifier type.
 * @param <N>    node's identifier type.
 */
record NodeEdgeWeight<E, N>(N node, E edge, Integer weight) {
}
