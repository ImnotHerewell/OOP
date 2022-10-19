package valikov.grlib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import valikov.grlib.representation.Matrix;

/**
 * Test with incidence matrix graph representation.
 */
public class IncidenceMatrixTest {
    private DefaultGraph<String, Integer> graph;

    @BeforeEach
    void readIncidenceMatrix() {
        String fileName = "./txt/IncidenceMatrix.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(
                     Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader);
             Scanner scan = new Scanner(reader)) {
            int nodeCount = scan.nextInt();
            int edgeCount = scan.nextInt();
            EdgesAndTos<String, Integer> edgeAndNodeList = new EdgesAndTos<>();
            List<Integer> nodeIdentifiers = new ArrayList<>();
            Matrix incidenceMatrix = new Matrix(nodeCount, edgeCount);
            for (int indexNode = 0; indexNode < nodeCount; indexNode++) {
                nodeIdentifiers.add(scan.nextInt());
            }
            for (int indexEdge = 0; indexEdge < edgeCount; indexEdge++) {
                edgeAndNodeList.add(scan.next(), scan.nextInt());
            }
            for (int indexRow = 0; indexRow < nodeCount; indexRow++) {
                for (int indexColumn = 0; indexColumn < edgeCount; indexColumn++) {
                    Integer weight = scan.nextInt();
                    incidenceMatrix.set(indexRow, indexColumn, weight);
                }
            }
            graph = new DefaultGraph<>(edgeAndNodeList, nodeIdentifiers, incidenceMatrix, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        List<NodeValue<Integer>> expList = new ArrayList<>();
        expList.add(new NodeValue<>(1, 0));
        expList.add(new NodeValue<>(2, 7));
        expList.add(new NodeValue<>(4, 7));
        expList.add(new NodeValue<>(3, 8));
        expList.add(new NodeValue<>(5, 103));
        List<NodeValue<Integer>> dijkstraList = graph.dijkstraAlgo(1);
        Assertions.assertEquals(expList, dijkstraList);
    }
}
