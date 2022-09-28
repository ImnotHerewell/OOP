package ru.nsu.valikov;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

public class TreeTest {
    @Test
    void main() {
    }

    @Test
    void get() {
    }

    @Test
    void add() {
        Tree <String> kek=new Tree<>();
        Tree <String> tet=new Tree<>();
        kek.add("B");
        kek.add("A");
        kek.add("C");
        Collection<Tree<String>> collection = Collections.singleton(kek);
        Stream<Tree<String>> stream = collection.stream();
        Optional<Tree<String>> test= collection.stream().findFirst();
        System.out.println(test);
    }

    @Test
    void testAdd() {
    }

    @Test
    void iterator() {
    }
}
