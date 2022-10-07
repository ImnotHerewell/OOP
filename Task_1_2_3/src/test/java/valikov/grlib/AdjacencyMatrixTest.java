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

// n cities
// m edges
// list of nodes identifers
// list of edges identifiers
// adjacencyMatrix
//...
public class AdjacencyMatrixTest {

    private File file;
    private Scanner scan;
    private Graph<String, Integer> graph;
    private int nodeCount;
    private int edgeCount;
    private List<Integer> nodeIdentifiers;
    private List<String> edgeIdentifiers;
    private List<List<Integer>> adjacencyMatrix;

    @BeforeEach
    void readAdjacencyMatrix() throws FileNotFoundException {
        file = new File("./txt/AdjacencyMatrixTxt.txt");
        scan = new Scanner(file);
        nodeCount = scan.nextInt();
        edgeCount = scan.nextInt();
        nodeIdentifiers = new ArrayList<>();
        edgeIdentifiers = new ArrayList<>();
        adjacencyMatrix = new ArrayList<>();
        for (int indexNode = 0; indexNode < nodeCount; indexNode++) {
            adjacencyMatrix.add(new ArrayList<>());
            for (int indexList = 0; indexList < 5; indexList++) {
                adjacencyMatrix.get(indexNode).add(Integer.MIN_VALUE);
            }
            nodeIdentifiers.add(scan.nextInt());
        }
        scan.nextLine();
        for (int indexEdge = 0; indexEdge < edgeCount; indexEdge++) {
            edgeIdentifiers.add(scan.nextLine());
        }
        for (int indexRow = 0; indexRow < nodeCount; indexRow++) {
            for (int indexColumn = 0; indexColumn < nodeCount; indexColumn++) {
                adjacencyMatrix.get(indexRow).set(indexColumn, scan.nextInt());
            }
        }
        graph = new Graph<>(nodeIdentifiers, edgeIdentifiers, adjacencyMatrix);
    }


    @Test
    void addNode() {
        HashMap<Integer, Node<String, Integer>> expNodeMap = graph.getMapOfAllNodes();
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
