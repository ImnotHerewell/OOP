package valikov.grlib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// дженерики только для вершин, типа вершина может быть типа стринг дефолт или какой-то объект, с вэйлью не, хотя в теории да.
public class Graph<T> {
    private Node<T> node;
    private HashMap<Node<T>, HashSet<HashSetValue<T>>> setOfAllNodes; // чтобы быстро менять ноды
    private HashSet<Edge<T>> map; // чтобы быстро менять ребра
    public void addNode(T node){
    }
}
