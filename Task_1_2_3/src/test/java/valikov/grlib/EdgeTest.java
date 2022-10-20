package valikov.grlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import valikov.grlib.intgraph.Edge;
import valikov.grlib.intgraph.Node;

class EdgeTest {

    private Edge<String, Integer> edge;

    @BeforeEach
    void setupEdge() {
        edge = new Edge<>("a", new Node<>(123), new Node<>(321), 10);
    }

    @Test
    void setIdentifier() {
        edge.setIdentifier("b");
        Assertions.assertEquals("b", edge.getIdentifier());
    }

    @Test
    void getIdentifier() {
        Assertions.assertEquals("a", edge.getIdentifier());
    }

    @Test
    void setStart() {
        Node<String, Integer> node = new Node<>(1);
        edge.setStart(node);
        Assertions.assertEquals(edge.getStart(), node);
    }

    @Test
    void getStart() {
        Node<String, Integer> node = new Node<>(123);
        Assertions.assertEquals(edge.getStart(), node);

    }

    @Test
    void setEnd() {
        Node<String, Integer> node = new Node<>(1);
        edge.setEnd(node);
        Assertions.assertEquals(edge.getEnd(), node);
    }

    @Test
    void getEnd() {
        Assertions.assertEquals(edge.getEnd().getIdentifier(), 321);
    }

    @Test
    void setWeight() {
        edge.setWeight(100);
        Assertions.assertEquals(edge.getWeight(), 100);
    }

    @Test
    void getWeight() {
        Assertions.assertEquals(edge.getWeight(), 10);
    }
}