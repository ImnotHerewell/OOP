package valikov.grlib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import valikov.grlib.representation.AdjacencyList;

/**
 * Test with adjacency list graph representation.
 */
public class AdjacencyListTest {
    private DefaultGraph<String, Integer> graph;

    @BeforeEach
    void readAdjacencyList() {
        String fileName = "./txt/AdjacencyList.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(
                     Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader);
             Scanner scan = new Scanner(reader)) {
            int nodeCount = scan.nextInt();
            AdjacencyList<String, Integer> adjacencyList
                    = new AdjacencyList<>();
            for (int indexNode = 0; indexNode < nodeCount; indexNode++) {
                Integer startNode = scan.nextInt();
                int edgeCount = scan.nextInt();
                NodeAndAdjacencies<String, Integer> adjacencies
                        = new NodeAndAdjacencies<>(startNode);
                for (int indexEdge = 0; indexEdge < edgeCount; indexEdge++) {
                    NodeEdgeWeight<String, Integer> noew
                            = new NodeEdgeWeight<>(scan.nextInt(), scan.next(), scan.nextInt());
                    adjacencies.add(noew);
                }
                adjacencyList.addEdge(startNode, adjacencies);
            }
            graph = new DefaultGraph<>(adjacencyList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getNode() {
        Node<String, Integer> nodeOne = graph.getNode(3);
        Node<String, Integer> nodeTwo = new Node<>(3);
        Assertions.assertEquals(nodeOne, nodeTwo);
    }

    @Test
    void addEdge() {
        HashMap<String, Edge<String, Integer>> expEdgeMap = (HashMap<String,
                Edge<String, Integer>>) graph.getMapOfAllEdges();
        graph.addEdge("k", 1, 2, 0);
        expEdgeMap.put("k", new Edge<>("k", graph.getNode(1),
                graph.getNode(2), 0));
        Assertions.assertEquals(graph.getMapOfAllEdges(), expEdgeMap);
    }

    @Test
    void removeEdge() {
        graph.removeEdge("a");
        graph.removeEdge("b");
        graph.removeEdge("c");
        graph.removeEdge("d");
        graph.removeEdge("e");
        graph.removeEdge("f");
        graph.removeEdge("g");
        graph.removeEdge("h");
        graph.removeEdge("i");
        graph.removeEdge("j");
        DefaultGraph<String, Integer> secondGraph = new DefaultGraph<>();
        secondGraph.addNode(1);
        secondGraph.addNode(2);
        secondGraph.addNode(3);
        secondGraph.addNode(4);
        secondGraph.addNode(5);
        Assertions.assertEquals(secondGraph, graph);
    }

    @Test
    void setEdgeIdentifier() {
        graph.setEdgeIdentifier("a", "aa");
        graph.setEdgeIdentifier("b", "bb");
        Assertions.assertEquals(graph.getEdgeIdentifier("aa"), "aa");
        Assertions.assertEquals(graph.getEdgeIdentifier("bb"), "bb");
    }
}
