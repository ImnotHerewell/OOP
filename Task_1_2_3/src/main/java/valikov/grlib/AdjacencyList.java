package valikov.grlib;

import java.util.HashMap;
import java.util.Map;

public class AdjacencyList<E, N> {
    private final Map<N, NodeAndAdjacencies<E, N>> map=new HashMap<>();

    public Map<N, NodeAndAdjacencies<E, N>> getMap() {
        return map;
    }
    public void addEdge(N identifier, NodeAndAdjacencies<E, N> adjacencies){
        map.put(identifier, adjacencies);
    }

}
