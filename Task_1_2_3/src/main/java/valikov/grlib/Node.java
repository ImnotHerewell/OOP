package valikov.grlib;

import java.util.ArrayList;
import java.util.List;

//nado
class Node<T> {
    private List<Edge<T>> listOfEdges;

    Node(){
        listOfEdges=new ArrayList<>();
    }
}
