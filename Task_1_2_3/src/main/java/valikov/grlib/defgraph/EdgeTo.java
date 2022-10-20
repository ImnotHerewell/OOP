package valikov.grlib.defgraph;

/**
 * To where edge comes.
 *
 * @param edge edge from Edge class.
 * @param to node from Node class.
 * @param <E> edge's identifier.
 * @param <N> node's identifier.
 */
public record EdgeTo<E, N>(E edge, N to) {
}