//package valikov.grlib;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class PairTest {
//
//    Pair<Integer, String> pair;
//
//    @BeforeEach
//    void setup() {
//        pair = new Pair<>(0, "null");
//    }
//
//    @Test
//    void setFirst() {
//        pair.setFirst(1);
//        Assertions.assertEquals(pair.getFirst(), 1);
//    }
//
//    @Test
//    void getFirst() {
//        Assertions.assertEquals(pair.getFirst(), 0);
//    }
//
//    @Test
//    void setSecond() {
//        pair.setSecond("non-null");
//        Assertions.assertEquals(pair.getSecond(), "non-null");
//    }
//
//    @Test
//    void getSecond() {
//        Assertions.assertEquals(pair.getSecond(), "null");
//    }
//
//    @Test
//    void testEquals() {
//        Pair<Integer, String> newPair = new Pair<>(0, "null");
//        Assertions.assertEquals(pair, newPair);
//    }
//
//    @Test
//    void testHashCode() {
//        Pair<Integer, String> newPair = new Pair<>(0, "null");
//        Assertions.assertEquals(pair.hashCode(), newPair.hashCode());
//    }
//}