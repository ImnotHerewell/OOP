package valikov.grlib.defgraph;

public record NodeValue<N>(N first, Integer second) implements Comparable<NodeValue<N>> {


    /**
     * Compares two NodeValues.
     *
     * @param o the object to be compared.
     * @return positive if second is more, negative if second is less, else 0.
     */
    @Override
    public int compareTo(NodeValue<N> o) {
        return second - o.second;
    }
}