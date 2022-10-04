package valikov.grlib;

import java.util.*;

// дженерики только для вершин, типа вершина может быть типа стринг дефолт или какой-то объект, с вэйлью не, хотя в теории да.
public class Graph<T> {
    private Node<T> node;
    HashSet<Node<T>> setOfAllNodes;
    private HashSet<Edge<T>> setOfALlEdges; // быстрее по complexity и проще, больше памяти требует
    public void addNode(T node){
        setOfAllNodes.
    }
}
