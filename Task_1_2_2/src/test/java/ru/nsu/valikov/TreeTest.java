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
        Node<String> expNode10 = bambooExp.add(expNode9, "10");
        Node<String> node8 = bambooTest.add(node7, "8");
        Node<String> node9 = bambooTest.add(node8, "9");
        Node<String> node10 = bambooTest.add(node9, "10");
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
        Node<String> testNode5 = bambooTest.add(testNode4, "11111");
        for (String str : bambooTest) {
            bambooTest.erase(str);
        }
        Assertions.assertEquals(emptyTree.hashCode(), bambooTest.hashCode());
    }
}
