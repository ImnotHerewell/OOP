package valikov.grlib;

import java.util.Objects;

class Pair<E, N> {
    private E first;
    private N second;

    Pair(E first, N second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(E first) {
        this.first = first;
    }

    public E getFirst() {
        return first;
    }

    public void setSecond(N second) {
        this.second = second;
    }

    public N getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked")
        Pair<E, N> pair = (Pair<E, N>) o;
        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
