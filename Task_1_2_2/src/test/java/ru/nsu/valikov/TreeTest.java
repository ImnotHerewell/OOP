package ru.nsu.valikov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Just class with some tests.
 */
public class TreeTest {
    /**
     * Graph-bamboo.
     */
    private void setupBamboo(Tree<String> bambooTest) {
        bambooTest.add("1");
        Node<String> node3 = bambooTest.add("3");
        Node<String> node4 = bambooTest.add(node3, "4");
        Node<String> node5 = bambooTest.add(node4, "5");
        bambooTest.add(node5, "6");
        bambooTest.add(node5, "7");
        bambooTest.erase("4");
        bambooTest.erase("6");
        bambooTest.erase("2");
    }

    private void setupGraphDefault(Tree<String> test) {
        test.add("1");
        test.add("3");
        Node<String> node4 = test.add("4");
        Node<String> node5 = test.add("5");
        test.add(node5, "6");
        test.add(node4, "7");
        test.erase("4");
        test.erase("6");
    }

    /**
     * Testing add and erase.
     */
    @Test
    public void testAddErase() {
        Tree<String> bambooTest = new Tree<>();
        setupBamboo(bambooTest);
        Tree<String> bambooExp = new Tree<>();
        bambooExp.add("1");
        Node<String> expNode3 = bambooExp.add("3");
        Node<String> expNode5 = bambooExp.add(expNode3, "5");
        bambooExp.add(expNode5, "7");
        Assertions.assertEquals(bambooExp.hashCode(), bambooTest.hashCode());
        Tree<String> test = new Tree<>();
        setupGraphDefault(test);
        Tree<String> exp = new Tree<>();
        exp.add("1");
        exp.add("7");
        exp.add("3");
        exp.add("5");
        Assertions.assertEquals(exp, test);
    }

    /**
     * Testing bfsIterator.
     */
    @Test
    public void testBfsIterator() {
        Tree<String> bambooTest = new Tree<>();
        setupBamboo(bambooTest);
        Iterator<String> bfsIterator = bambooTest.iteratorBfs();
        List<String> listBamboo = new ArrayList<>();
        while (bfsIterator.hasNext()) {
            listBamboo.add(bfsIterator.next());
        }
        for (String stringErase : listBamboo){
            bambooTest.erase(stringErase);
        }
        Tree<String> emptyTree = new Tree<>();
        Assertions.assertEquals(emptyTree, bambooTest);
        Tree<String> test = new Tree<>();
        setupGraphDefault(test);
        Iterator<String> bfsIteratorTree = test.iteratorBfs();
        List<String> listDefault = new ArrayList<>();
        while (bfsIteratorTree.hasNext()) {
            listDefault.add(bfsIteratorTree.next());
        }
        for (String stringErase : listDefault){
            test.erase(stringErase);
        }
        Tree<String> emptyTreeDef = new Tree<>();
        Assertions.assertEquals(emptyTreeDef, test);
    }

    /**
     * Testing dfsIterator.
     */
    @Test
    public void testDfsIterator() {
        Tree<String> bambooTest = new Tree<>();
        setupBamboo(bambooTest);
        List<String> listBamboo = new ArrayList<>();
        for (String dfsString : bambooTest) {
            listBamboo.add(dfsString);
        }
        for (String stringErase : listBamboo){
            bambooTest.erase(stringErase);
        }
        Tree<String> emptyTree = new Tree<>();
        Assertions.assertEquals(emptyTree, bambooTest);
        Tree<String> test = new Tree<>();
        setupGraphDefault(test);
        List<String> listDefault = new ArrayList<>();
        for (String dfsString : test) {
            listDefault.add(dfsString);
        }
        for (String stringErase : listDefault){
            test.erase(stringErase);
        }
        Assertions.assertEquals(emptyTree, test);
    }

    /**
     * Test two equal tree on equality.
     */
    @Test
    public void equalTest() {
        Tree<Integer> expTree = new Tree<>();
        expTree.add(1);
        expTree.add(2);
        expTree.add(3);
        expTree.add(4);
        expTree.add(5);
        expTree.add(6);
        expTree.add(7);
        expTree.add(8);
        expTree.add(9);
        expTree.add(10);
        Tree<Integer> testTree = new Tree<>();
        testTree.add(2);
        testTree.add(7);
        testTree.add(5);
        testTree.add(4);
        testTree.add(1);
        testTree.add(10);
        testTree.add(8);
        testTree.add(3);
        testTree.add(6);
        testTree.add(9);
        Assertions.assertEquals(testTree, expTree);
    }

}
