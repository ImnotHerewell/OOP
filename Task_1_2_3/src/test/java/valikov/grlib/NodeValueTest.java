package valikov.grlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import valikov.grlib.defgraph.NodeValue;

class NodeValueTest {

    @Test
    void compareTo() {
        NodeValue<Integer> nodeValueFirst = new NodeValue<>(0, 1);
        NodeValue<Integer> nodeValueSecond = new NodeValue<>(0, 2);
        Assertions.assertTrue(nodeValueFirst.compareTo(nodeValueSecond) < 0);
        nodeValueSecond = new NodeValue<>(0, 1);
        Assertions.assertEquals(0, nodeValueFirst.compareTo(nodeValueSecond));
        nodeValueSecond = new NodeValue<>(0, 0);
        Assertions.assertTrue(nodeValueFirst.compareTo(nodeValueSecond) > 0);
    }
}