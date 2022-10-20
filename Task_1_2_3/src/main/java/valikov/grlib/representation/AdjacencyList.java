package valikov.grlib.representation;

import java.util.HashMap;
import java.util.Map;
import valikov.grlib.intgraph.NodeAndAdjacencies;

/**
 * Adjacency list graph's representation.
 *
 * @param <E> edge's identifier.
 * @param <N> node's identifier.
 */
public class AdjacencyList<E, N> {
    private final Map<N, NodeAndAdjacencies<E, N>> map = new HashMap<>();

    public Map<N, NodeAndAdjacencies<E, N>> getMap() {
        return map;
    }

    public void addEdge(N identifier, NodeAndAdjacencies<E, N> adjacencies) {
        map.put(identifier, adjacencies);
    }

}
