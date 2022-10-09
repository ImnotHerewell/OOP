package valikov.grlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdjacencyMatrixTest {

    private Graph<String, Integer> graph;

    @BeforeEach
    void readAdjacencyMatrix() throws FileNotFoundException {
        File file = new File("./txt/AdjacencyMatrix.txt");
        Scanner scan = new Scanner(file);
        int nodeCount = scan.nextInt();
        int edgeCount = scan.nextInt();
        Pair<List<String>, List<Integer>> edgeNodeIdentifiers =
                new Pair<>(new ArrayList<>(), new ArrayList<>());
        List<List<Integer>> adjacencyMatrix = new ArrayList<>();
        for (int indexNode = 0; indexNode < nodeCount; indexNode++) {
            adjacencyMatrix.add(new ArrayList<>());
            for (int indexList = 0; indexList < nodeCount; indexList++) {
                adjacencyMatrix.get(indexNode).add(Integer.MIN_VALUE);
            }
            edgeNodeIdentifiers.getSecond().add(scan.nextInt());
        }
        for (int indexEdge = 0; indexEdge < edgeCount; indexEdge++) {
            edgeNodeIdentifiers.getFirst().add(scan.next());
        }
        for (int indexRow = 0; indexRow < nodeCount; indexRow++) {
            for (int indexColumn = 0; indexColumn < nodeCount; indexColumn++) {
                adjacencyMatrix.get(indexRow).set(indexColumn, scan.nextInt());
            }
        }
        graph = new Graph<>(edgeNodeIdentifiers, adjacencyMatrix);
    }


    @Test
    void addNode() {
        @SuppressWarnings("unchecked")
        HashMap<Integer, Node<String, Integer>> expNodeMap = (HashMap<Integer,
                Node<String, Integer>>) graph.getMapOfAllNodes().clone();
        Integer newIdentifier = 123;
        graph.addNode(newIdentifier);
        expNodeMap.put(newIdentifier, new Node<>(newIdentifier));
        Assertions.assertEquals(graph.getMapOfAllNodes(), expNodeMap);
    }

    @Test
    void removeNode() {
        graph.removeNode(5);
        graph.removeNode(4);
        graph.removeNode(3);
        graph.removeNode(2);
        graph.removeNode(1);
        Graph<String, Integer> secondGraph = new Graph<>();
        Assertions.assertEquals(secondGraph, graph);
    }

    @Test
    void setNodeIdentifier() {
        graph.setNodeIdentifier(1, 10);
        graph.setNodeIdentifier(2, 20);
        Assertions.assertEquals(graph.getNodeIdentifier(20), 20);
        Assertions.assertEquals(graph.getNodeIdentifier(10), 10);
    }

    @Test
    void getNodeIdentifier() {
        Assertions.assertEquals(graph.getNodeIdentifier(1), 1);
    }


}
