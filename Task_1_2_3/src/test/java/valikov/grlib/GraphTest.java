package valikov.grlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private File file;
    private Scanner scan;
    private int nodeCount;
    private int edgeCount;
    private List<Integer> nodeIdentifiers;
    private List<String> edgeIdentifiers;
    private List<List<Integer>> matrix;

    private Graph<String, Integer> readAdjacencyMatrix() throws FileNotFoundException {
        file = new File("./txt/AdjacencyMatrixTxt.txt");
        scan = new Scanner(file);
//        System.out.println(scan.nextInt());
        nodeCount = scan.nextInt();
//        System.out.println(scan.nextInt());
        edgeCount = scan.nextInt();
//        System.out.println(scan.nextInt());
        nodeIdentifiers = new ArrayList<>();
        edgeIdentifiers = new ArrayList<>();
        matrix = new ArrayList<>();
        for (int indexNode = 0; indexNode < nodeCount; indexNode++) {
            matrix.add(new ArrayList<>());
            for (int indexList=0;indexList<5;indexList++){
                matrix.get(indexNode).add(Integer.MIN_VALUE);
            }
            nodeIdentifiers.add(scan.nextInt());
        }
        scan.nextLine();
        for (int indexEdge = 0; indexEdge < edgeCount; indexEdge++) {
//            System.out.println(scan.nextLine());
            edgeIdentifiers.add(scan.nextLine());
        }
        for (int indexRow = 0; indexRow < nodeCount; indexRow++) {
            for (int indexColumn = 0; indexColumn < nodeCount; indexColumn++) {
//                System.out.println(scan.nextInt());
                matrix.get(indexRow).set(indexColumn, scan.nextInt());
            }
        }
        return new Graph<>(nodeIdentifiers, edgeIdentifiers, matrix);
    }


    @Test
    void addNodeAdjacencyMatrix() throws FileNotFoundException {
        Graph<String, Integer> graph=readAdjacencyMatrix();
//        System.out.println("kekes");
        HashMap<Integer, Node<String, Integer>> expNodeMap=graph.getMapOfAllNodes();
        Integer newIdentifier=123;
        graph.addNode(newIdentifier);
        expNodeMap.put(newIdentifier, new Node<>(newIdentifier));
        Assertions.assertEquals(graph.getMapOfAllNodes(), expNodeMap);
    }

    @Test
    void removeNodeAdjacencyMatrix() {
    }

    @Test
    void setNodeIdentifier() {
    }

    @Test
    void getNodeIdentifier() {
    }

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

    @Test
    void getEdgeIdentifier() {
    }

    @Test
    void getEdge() {
    }

    @Test
    void nodeCount() {
    }

    @Test
    void edgeCount() {
    }

    @Test
    void dijkstra() {
    }
}