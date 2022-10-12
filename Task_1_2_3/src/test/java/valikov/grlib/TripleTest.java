package valikov.grlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TripleTest {
    Triple<Integer, String, Boolean> triplet;

    @BeforeEach
    void setup() {
        triplet = new Triple<>(0, "null", false);
    }

    @Test
    void setThird() {
        triplet.setThird(true);
        Assertions.assertEquals(triplet.getThird(), true);
    }

    @Test
    void getThird() {
        Assertions.assertEquals(triplet.getThird(), false);
    }
}