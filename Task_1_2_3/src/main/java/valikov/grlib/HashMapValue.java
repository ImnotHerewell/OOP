package valikov.grlib;

public class HashMapValue<T, Integer> {
    private final T node;
    private final Integer value;
    HashMapValue(T node, Integer value){
        this.node=node;
        this.value=value;
    }

    public T getNode() {
        return node;
    }

    public Integer getValue() {
        return value;
    }
}
