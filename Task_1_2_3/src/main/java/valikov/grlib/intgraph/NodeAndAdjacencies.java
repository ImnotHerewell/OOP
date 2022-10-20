package valikov.grlib.intgraph;

import java.util.ArrayList;
import java.util.List;
import valikov.grlib.intgraph.NodeEdgeWeight;

/**
 * Node and its adjacency nodes.
 *
 * @param <E> edge's identifier type.
 * @param <N> node's identifier type.
 */
public class NodeAndAdjacencies<E, N> {
    private final N currentNode;
    private final List<NodeEdgeWeight<E, N>> adjacencyNodes;

    public NodeAndAdjacencies(N currentNode) {
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

