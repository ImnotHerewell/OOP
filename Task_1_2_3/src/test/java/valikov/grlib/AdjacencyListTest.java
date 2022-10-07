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

//n cities
// m edges for every node
// adjacency list (first identifier of curNode, identifier of edge, identifier of anotherNode, weight)
//...
public class AdjacencyListTest {
    private Graph<String, Integer> graph;

    @BeforeEach
    void readAdjacencyList() throws FileNotFoundException {
        File file = new File("./txt/AdjacencyList.txt");
        Scanner scan = new Scanner(file);
        int nodeCount = scan.nextInt();
        List<Pair<Integer, List<Triple<Integer, String, Integer>>>> adjacencyList = new ArrayList<>();
        for (int indexNode = 0; indexNode < nodeCount; indexNode++) {
            adjacencyList.add(new Pair<>(scan.nextInt(), new ArrayList<>()));
            int edgeCount = scan.nextInt();
            for (int indexEdge = 0; indexEdge < edgeCount; indexEdge++) {
                adjacencyList.get(indexNode).getSecond().add(new Triple<>(scan.nextInt(), scan.next(), scan.nextInt()));
            }
        }
        graph = new Graph<>(adjacencyList);
    }

    @Test
    void getNode() {
        Node<String, Integer> nodeOne = graph.getNode(3);
        Node<String, Integer> nodeTwo = new Node<>(3);
        Assertions.assertEquals(nodeOne, nodeTwo);
    }

    @Test
    void addEdge() {
        @SuppressWarnings("unchecked")
        HashMap<String, Edge<String, Integer>> expEdgeMap = (HashMap<String, Edge<String, Integer>>) graph.getMapOfAllEdges().clone();
        graph.addEdge("k", 1, 2, 0);
        expEdgeMap.put("k", new Edge<>("k", graph.getNode(1), graph.getNode(2), 0));
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
        Graph<String, Integer> secondGraph = new Graph<>();
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
