package valikov.grlib;

import java.util.ArrayList;
import java.util.List;
import valikov.grlib.records.EdgeTo;

class EdgesAndTos<E, N> {
    List<EdgeTo<E, N>> list = new ArrayList<>();

    public void add(E identifier, N to) {
        list.add(new EdgeTo<>(identifier, to));
    }
    public EdgeTo<E, N> get(Integer index){
        return list.get(index);
    }
}
