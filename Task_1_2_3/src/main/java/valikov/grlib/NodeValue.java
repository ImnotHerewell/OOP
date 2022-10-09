package valikov.grlib;

import java.util.Objects;

/**
 * @param <N>
 */
class NodeValue<N> extends Pair<N, Integer> implements Comparable<NodeValue<N>> {
    NodeValue(N first, Integer second) {
        super(first, second);
    }


    /**
     * Compares two NodeValues.
     *
     * @param o the object to be compared.
     * @return positive if second is more, negative if second is less, else 0.
     */
    @Override
    public int compareTo(NodeValue<N> o) {
        return getSecond() - o.getSecond();
    }

    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        NodeValue<N> obj = (NodeValue<N>) o;
        return Objects.equals(obj.getSecond(), getSecond());
    }
}
