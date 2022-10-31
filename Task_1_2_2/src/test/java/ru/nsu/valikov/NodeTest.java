package ru.nsu.valikov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NodeTest {
    Node<String> currentNode;
    Node<String> parentNode;

    @BeforeEach
    void nodeSetup() {
        currentNode = new Node<>();
        parentNode = new Node<>();
        currentNode.setValue("kek");
        parentNode.setValue("parent kek");
        currentNode.setParent(parentNode);
    }

    @Test
    void setValue() {
        currentNode.setValue("not kek");
        Assertions.assertEquals(currentNode.getValue(), "not kek");
    }

    @Test
    void getValue() {
        Assertions.assertEquals(currentNode.getValue(), "kek");
    }

    @Test
    void setParent() {
        Node<String> newParent = new Node<>();
        newParent.setValue("not kek parent");
        currentNode.setParent(newParent);
        Assertions.assertEquals(currentNode.getParent(), newParent);
    }

    @Test
    void getParent() {
        Assertions.assertEquals(parentNode, currentNode.getParent());
    }

}