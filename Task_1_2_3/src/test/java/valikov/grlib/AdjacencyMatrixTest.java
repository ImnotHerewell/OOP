package valikov.grlib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import valikov.grlib.defgraph.DefaultGraph;
import valikov.grlib.intgraph.EdgesAndTos;
import valikov.grlib.intgraph.Node;
import valikov.grlib.representation.Matrix;

/**
 * Test with adjacency matrix graph representation.
 */
public class AdjacencyMatrixTest {

    private DefaultGraph<String, Integer> graph;

    @BeforeEach
    void readAdjacencyMatrix() {
        String fileName = "./txt/AdjacencyMatrix.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(
                     Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader);
             Scanner scan = new Scanner(reader)) {
            int nodeCount = scan.nextInt();
            int edgeCount = scan.nextInt();
            EdgesAndTos<String, Integer> edgeIdentifiers =
                    new EdgesAndTos<>();
            List<Integer> nodeIdentifiers = new ArrayList<>();
            Matrix adjacencyMatrix = new Matrix(nodeCount, edgeCount);
            for (int indexNode = 0; indexNode < nodeCount; indexNode++) {
                nodeIdentifiers.add(scan.nextInt());
            }
            for (int indexEdge = 0; indexEdge < edgeCount; indexEdge++) {
                edgeIdentifiers.add(scan.next(), scan.nextInt());
            }
            for (int indexRow = 0; indexRow < nodeCount; indexRow++) {
                for (int indexColumn = 0; indexColumn < nodeCount; indexColumn++) {
                    adjacencyMatrix.set(indexRow, indexColumn, scan.nextInt());
                }
            }
            graph = new DefaultGraph<>(edgeIdentifiers, nodeIdentifiers, adjacencyMatrix, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void addNode() {
        @SuppressWarnings("unchecked")
        HashMap<Integer, Node<String, Integer>> expNodeMap = (HashMap<Integer,
                Node<String, Integer>>) graph.getMapOfAllNodes();
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
        DefaultGraph<String, Integer> secondGraph = new DefaultGraph<>();
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
