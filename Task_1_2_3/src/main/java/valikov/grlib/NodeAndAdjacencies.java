package valikov.grlib;

import java.util.ArrayList;
import java.util.List;

public class NodeAndAdjacencies<E, N> {
    private N currentNode;
    private List<NodeEdgeWeight<E, N>> adjacencyNodes;

    NodeAndAdjacencies(N currentNode){
        this.currentNode=currentNode;
        adjacencyNodes=new ArrayList<>();
    }

    public N getCurrentNode() {
        return currentNode;
    }

    public List<NodeEdgeWeight<E, N>> getAdjacencyNodes() {
        return adjacencyNodes;
    }
    public void add(NodeEdgeWeight<E, N> noew){
        adjacencyNodes.add(noew);
    }
}
