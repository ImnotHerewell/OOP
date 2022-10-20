package valikov.grlib.intgraph;

/**
 * Record for adjacency node, its edge and edge's weight.
 *
 * @param node   node's identifier.
 * @param edge   edge's identifier.
 * @param weight edge's weight.
 * @param <E>    edge's identifier type.
 * @param <N>    node's identifier type.
 */
public record NodeEdgeWeight<E, N>(N node, E edge, Integer weight) {
}
