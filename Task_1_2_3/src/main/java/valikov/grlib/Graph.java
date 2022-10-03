package valikov.grlib;

import java.util.HashMap;
import java.util.List;

// дженерики только для вершин, типа вершина может быть типа стринг дефолт или какой-то объект, с вэйлью не, хотя в теории да.
public class Graph<T> {
    private HashMap<T, List<HashMapValue>> map;
}
