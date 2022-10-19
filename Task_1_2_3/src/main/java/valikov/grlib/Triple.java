package valikov.grlib;

/**
 * Class for adjacency list representation.
 *
 * @param <E> edge's identifier type
 * @param <N> node's identifier type
 * @param <K> third param's identifier
 */
public record Triple<E, N, K>(E first, N second, K third) {
}
