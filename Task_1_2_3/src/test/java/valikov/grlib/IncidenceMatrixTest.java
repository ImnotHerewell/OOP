package valikov.grlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test with incidence matrix graph representation.
 */
public class IncidenceMatrixTest {
    private Graph<String, Integer> graph;

    @BeforeEach
    void readIncidenceMatrix() throws FileNotFoundException {
        File file = new File("./txt/IncidenceMatrix.txt");
        Scanner scan = new Scanner(file);
        int nodeCount = scan.nextInt();
        int edgeCount = scan.nextInt();
        List<Pair<String, Integer>> edgeAndNodeList = new ArrayList<>();
        List<Integer> nodeIdentifiers = new ArrayList<>();
        List<List<Integer>> incidenceMatrix = new ArrayList<>();
        for (int indexNode = 0; indexNode < nodeCount; indexNode++) {
            incidenceMatrix.add(new ArrayList<>());
            for (int indexList = 0; indexList < edgeCount; indexList++) {
                incidenceMatrix.get(indexNode).add(Integer.MIN_VALUE);
            }
            nodeIdentifiers.add(scan.nextInt());
        }
        for (int indexEdge = 0; indexEdge < edgeCount; indexEdge++) {
            edgeAndNodeList.add(new Pair<>(scan.next(), scan.nextInt()));
        }
        for (int indexRow = 0; indexRow < nodeCount; indexRow++) {
            for (int indexColumn = 0; indexColumn < edgeCount; indexColumn++) {
                Integer weight = scan.nextInt();
                incidenceMatrix.get(indexRow).set(indexColumn, weight);
            }
        }
        graph = new Graph<>(edgeAndNodeList, nodeIdentifiers, incidenceMatrix, 0);
    }

    @Test
    void getEdgeIdentifier() {
        Assertions.assertEquals(graph.getEdgeIdentifier("a"), "a");
    }

    @Test
    void getEdge() {
        Node<String, Integer> nodeOne = new Node<>(1);
        Node<String, Integer> nodeTwo = new Node<>(2);
        Edge<String, Integer> edge = new Edge<>("a", nodeOne, nodeTwo, 7);
        Assertions.assertEquals(graph.getEdge("a"), edge);
    }

    @Test
    void getNode() {
        Node<String, Integer> node = new Node<>(1);
        Assertions.assertEquals(graph.getNode(1), node);
    }

    @Test
    void nodeCount() {
        Assertions.assertEquals(graph.nodeCount(), 5);
    }

    @Test
    void edgeCount() {
        Assertions.assertEquals(graph.edgeCount(), 7);
    }

    @Test
    void dijkstra() {
        List<NodeValue<Node<String, Integer>>> expList = new ArrayList<>();
        expList.add(new NodeValue<>(new Node<>(1), 0));
        expList.add(new NodeValue<>(new Node<>(2), 7));
        expList.add(new NodeValue<>(new Node<>(4), 7));
        expList.add(new NodeValue<>(new Node<>(3), 8));
        expList.add(new NodeValue<>(new Node<>(5), 103));
        List<NodeValue<Node<String, Integer>>> dijkstraList = graph.dijkstra(1);
        Assertions.assertEquals(expList, dijkstraList);
    }
}
