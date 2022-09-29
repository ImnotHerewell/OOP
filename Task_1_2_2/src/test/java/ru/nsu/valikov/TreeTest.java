package ru.nsu.valikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class TreeTest {
    @Test
    public void graphBamboo() {
        Tree<String> bambooTest = new Tree<>();
        bambooTest.add("1");
        Node<String> node3 = bambooTest.add("3");
        Node<String> node4 = bambooTest.add(node3, "4");
        Node<String> node5 = bambooTest.add(node4, "5");
        Node<String> node6 = bambooTest.add(node5, "6");
        Node<String> node7 = bambooTest.add(node6, "7");
        bambooTest.erase("4");
        bambooTest.erase("6");
        bambooTest.erase("2");

        Tree<String> bambooExp = new Tree<>();
        bambooExp.add("1");
        Node<String> expNode3 = bambooExp.add("3");
        Node<String> expNode5 = bambooExp.add(expNode3, "5");
        Node<String> expNode7 = bambooExp.add(expNode5, "7");
        Assertions.assertEquals(bambooExp.hashCode(), bambooTest.hashCode());

        Node<String> expNode8 = bambooExp.add(expNode7, "8");
        Node<String> expNode9 = bambooExp.add(expNode8, "9");
        bambooExp.add(expNode9, "10");
        Node<String> node8 = bambooTest.add(node7, "8");
        Node<String> node9 = bambooTest.add(node8, "9");
        bambooTest.add(node9, "10");
        Assertions.assertEquals(bambooExp.hashCode(), bambooTest.hashCode());
//
        Iterator<String> bfsIterator = bambooTest.iteratorBFS();
        while (bfsIterator.hasNext()) {
            bfsIterator.next();
            bfsIterator.remove();
        }
        Tree<String> emptyTree = new Tree<>();
        Assertions.assertEquals(emptyTree.hashCode(), bambooTest.hashCode());
        //
        Node<String> testNode1 = bambooTest.add("1");
        Node<String> testNode2 = bambooTest.add(testNode1, "11");
        Node<String> testNode3 = bambooTest.add(testNode2, "111");
        Node<String> testNode4 = bambooTest.add(testNode3, "1111");
        bambooTest.add(testNode4, "11111");
        for (String str : bambooTest) {
            bambooTest.erase(str);
        }
        Assertions.assertEquals(emptyTree.hashCode(), bambooTest.hashCode());
    }

    @Test
    public void graphDefault() {
        Tree<String> test = new Tree<>();
        test.add("1");
        Node<String> node3 = test.add("3");
        Node<String> node4 = test.add("4");
        Node<String> node5 = test.add("5");
        test.add(node5, "6");
        Node<String> node7 = test.add(node4, "7");
        test.erase("4");
        test.erase("6");

        Tree<String> exp = new Tree<>();
        exp.add("1");
        Node<String> expNode3 = exp.add("3");
        Node<String> expNode5 = exp.add("5");
        Node<String> expNode7 = exp.add("7");
        Assertions.assertEquals(exp.hashCode(), test.hashCode());

        Node<String> expNode8 = exp.add(expNode7, "8");
        Node<String> expNode9 = exp.add(expNode5, "9");
        Node<String> expNode10 = exp.add(expNode3, "10");
        Node<String> node8 = test.add(node7, "8");
        Node<String> node9 = test.add(node5, "9");
        Node<String> node10 = test.add(node3, "10");
        Assertions.assertEquals(exp.hashCode(), test.hashCode());
//
        Iterator<String> bfsIterator = test.iteratorBFS();
        while (bfsIterator.hasNext()) {
            bfsIterator.next();
            bfsIterator.remove();
        }
        Tree<String> emptyTree = new Tree<>();
        Assertions.assertEquals(emptyTree.hashCode(), test.hashCode());
        //
        Node<String> testNode1 = test.add("1");
        Node<String> testNode2 = test.add(testNode1, "11");
        Node<String> testNode3 = test.add(testNode2, "111");
        Node<String> testNode4 = test.add(testNode3, "1111");
        Node<String> testNode5 = test.add(testNode4, "11111");
        for (String str : test) {
            test.erase(str);
        }
        Assertions.assertEquals(emptyTree.hashCode(), test.hashCode());
    }


}
