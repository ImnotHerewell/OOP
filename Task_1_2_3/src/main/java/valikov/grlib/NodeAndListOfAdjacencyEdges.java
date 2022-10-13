package valikov.grlib;

/**
 * Class created for constructor for adjacency list.
 *
 * @param <E> - edge identifier
 * @param <N> - node identifier
 */
public class NodeAndListOfAdjacencyEdges<E, N> extends Pair<E, N> {
    NodeAndListOfAdjacencyEdges(E first, N second) {
        super(first, second);
    }
}
