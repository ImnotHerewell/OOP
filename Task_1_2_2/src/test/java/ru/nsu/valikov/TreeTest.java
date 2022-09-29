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
        Node<String> nNode3 = bambooExp.add("3");
        Node<String> nNode5 = bambooExp.add(nNode3, "5");
        Node<String> nNode7 = bambooExp.add(nNode5, "7");
        Assertions.assertEquals(bambooExp.hashCode(), bambooTest.hashCode());

        Node<String> node8 = bambooTest.add(node7, "8");
        Node<String> node9 = bambooTest.add(node8, "9");
        Node<String> node10 = bambooTest.add(node9, "10");
        Node<String> nNode8 = bambooExp.add(nNode7, "8");
        Node<String> nNode9 = bambooExp.add(nNode8, "9");
        Node<String> nNode10 = bambooExp.add(nNode9, "10");
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
        Node<String> cNode1 = bambooTest.add("1");
        Node<String> cNode2 = bambooTest.add(cNode1, "11");
        Node<String> cNode3 = bambooTest.add(cNode2, "111");
        Node<String> cNode4 = bambooTest.add(cNode3, "1111");
        Node<String> cNode5 = bambooTest.add(cNode4, "11111");
        for (String str : bambooTest) {
            bambooTest.erase(str);
        }
        Assertions.assertEquals(emptyTree.hashCode(), bambooTest.hashCode());
    }
}
