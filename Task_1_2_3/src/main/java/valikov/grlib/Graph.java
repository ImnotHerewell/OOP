package valikov.grlib;

import java.util.Map;

/**
 * Constructor for implementing different graphs.
 *
 * @param <E> edge's identifier.
 * @param <N> node's identifier.
 */
public interface Graph<E, N> {
    Map<N, Node<E, N>> getMapOfAllNodes();

    Map<E, Edge<E, N>> getMapOfAllEdges();
}
