package valikov.grlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NodeValueTest {

    @Test
    void compareTo() {
        NodeValue<Integer, Integer> nodeValueFirst = new NodeValue<>(0, 1);
        NodeValue<Integer, Integer> nodeValueSecond = new NodeValue<>(0, 2);
        Assertions.assertTrue(nodeValueFirst.compareTo(nodeValueSecond) < 0);
        nodeValueSecond.setSecond(1);
        Assertions.assertEquals(0, nodeValueFirst.compareTo(nodeValueSecond));
        nodeValueSecond.setSecond(0);
        Assertions.assertTrue(nodeValueFirst.compareTo(nodeValueSecond) > 0);
    }
}