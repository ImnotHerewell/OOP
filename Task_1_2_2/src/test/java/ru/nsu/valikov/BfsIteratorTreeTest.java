package ru.nsu.valikov;

import java.util.Iterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BfsIteratorTreeTest {
    Tree<String> testGraph = new Tree<>();
    Iterator<String> itTest;

    @BeforeEach
    void setup() {
        Node<String> node1 = testGraph.add("1");
        Node<String> node3 = testGraph.add("3");
        Node<String> node4 = testGraph.add(node1, "4");
        Node<String> node5 = testGraph.add(node3, "5");
        testGraph.add(node5, "6");
        testGraph.add(node4, "7");
        itTest = testGraph.iteratorBfs();
    }

    @Test
    void hasNext() {
        Assertions.assertTrue(itTest.hasNext());
    }

    @Test
    void next() {
        Assertions.assertEquals(itTest.next(), "1");
    }

    @Test
    void remove() {
        itTest.next();
        itTest.remove();
        Iterator<String> newIterator = testGraph.iteratorBfs();
        Assertions.assertEquals(newIterator.next(), "3");
    }
}