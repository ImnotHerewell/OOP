package valikov.grlib;

/**
 * @param <E>
 * @param <N>
 * @param <K>
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
