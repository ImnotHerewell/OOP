package valikov.grlib;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import valikov.grlib.intgraph.Edge;
import valikov.grlib.intgraph.Node;

class NodeTest {

    private Node<String, Integer> node;

    @BeforeEach
    void setupNode() {
        node = new Node<>(123);
    }

    @Test
    void setIdentifier() {
        node.setIdentifier(34);
        Assertions.assertEquals(node.getIdentifier(), 34);
    }

    @Test
    void getIdentifier() {
        Assertions.assertEquals(node.getIdentifier(), 123);
    }

    @Test
    void getListOfEdges() {
        Edge<String, Integer> edge = new Edge<>("a", node, new Node<>(321), 10);
        List<Edge<String, Integer>> edgeList = new ArrayList<>();
        node.addEdge(edge);
        edgeList.add(edge);
        Assertions.assertEquals(node.getListOfEdges(), edgeList);
    }

}