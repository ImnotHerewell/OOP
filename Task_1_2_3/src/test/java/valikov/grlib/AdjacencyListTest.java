package valikov.grlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//n cities
// m edges for every node
// adjacency list (first identifier of curNode, identifier of edge, identifier of anotherNode, weight)
//...
public class AdjacencyListTest {
    private File file;
    private Scanner scan;
    private Graph<String, Integer> graph;
    private int nodeCount;
    private int edgeCount;
    private List<Integer> nodeIdentifiers;
    private List<String> edgeIdentifiers;
    private List<List<Integer>> adjacencyMatrix;

//    @BeforeEach
//    void readAdjacencyList() throws FileNotFoundException {
//        file = new File("./AdjacencyMatrix.txt");
//        scan = new Scanner(file);
//        nodeCount = scan.nextInt();
//        edgeCount = scan.nextInt();
//        nodeIdentifiers = new ArrayList<>();
//        edgeIdentifiers = new ArrayList<>();
//        adjacencyMatrix = new ArrayList<>();
//        for (int indexNode = 0; indexNode < nodeCount; indexNode++) {
//            adjacencyMatrix.add(new ArrayList<>());
//            for (int indexList = 0; indexList < 5; indexList++) {
//                adjacencyMatrix.get(indexNode).add(Integer.MIN_VALUE);
//            }
//            nodeIdentifiers.add(scan.nextInt());
//        }
//        scan.nextLine();
//        for (int indexEdge = 0; indexEdge < edgeCount; indexEdge++) {
//            edgeIdentifiers.add(scan.nextLine());
//        }
//        for (int indexRow = 0; indexRow < nodeCount; indexRow++) {
//            for (int indexColumn = 0; indexColumn < nodeCount; indexColumn++) {
//                adjacencyMatrix.get(indexRow).set(indexColumn, scan.nextInt());
//            }
//        }
//        graph = new Graph<>(nodeIdentifiers, edgeIdentifiers, adjacencyMatrix);
//    }

    @Test
    void getNode() {
    }

    @Test
    void addEdge() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void setEdgeIdentifier() {
    }
}
