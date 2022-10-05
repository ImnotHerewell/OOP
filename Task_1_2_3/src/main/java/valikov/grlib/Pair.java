package valikov.grlib;

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

}
