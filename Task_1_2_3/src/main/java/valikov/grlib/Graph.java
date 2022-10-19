package valikov.grlib;

import java.util.Map;

public interface Graph<E, N> {
    Map<N, Node<E, N>> getMapOfAllNodes();

    Map<E, Edge<E, N>> getMapOfAllEdges();
}
