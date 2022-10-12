package valikov.grlib;

/**
 * Class for adjacency list representation.
 *
 * @param <E> edge's identifier type
 * @param <N> node's identifier type
 * @param <K> third param's identifier
 */
public class Triple<E, N, K> extends Pair<E, N> {
    private K third;

    Triple(E first, N second, K third) {
        super(first, second);
        setThird(third);
    }

    public void setThird(K third) {
        this.third = third;
    }

    public K getThird() {
        return third;
    }
}
