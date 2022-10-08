package valikov.grlib;

/**
 * @param <N>
 * @param <Integer>
 */
class NodeValue<N, Integer> extends Pair<N, Integer> implements Comparable<NodeValue<N, Integer>> {
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
    public int compareTo(NodeValue<N, Integer> o) {
        return (int) getSecond() - (int) o.getSecond();
    }

    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        NodeValue<N, Integer> obj = (NodeValue<N, Integer>) o;
        return obj.getSecond() == getSecond();
    }
}
