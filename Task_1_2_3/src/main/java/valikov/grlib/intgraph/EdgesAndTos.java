package valikov.grlib.intgraph;

import java.util.ArrayList;
import java.util.List;
import valikov.grlib.defgraph.EdgeTo;

public class EdgesAndTos<E, N> {
    public List<EdgeTo<E, N>> list = new ArrayList<>();

    public void add(E identifier, N to) {
        list.add(new EdgeTo<>(identifier, to));
    }

    public EdgeTo<E, N> get(Integer index) {
        return list.get(index);
    }
}
