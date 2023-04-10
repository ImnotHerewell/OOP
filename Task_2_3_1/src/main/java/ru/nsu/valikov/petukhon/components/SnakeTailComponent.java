package ru.nsu.valikov.petukhon.components;

import com.almasb.fxgl.entity.component.Component;


/**
 * Identifies snake's tail and its opportunities.
 */
public class SnakeTailComponent extends Component {

    private final SnakeHeadComponent parent;

    public SnakeTailComponent(SnakeHeadComponent parent) {
        this.parent = parent;
    }

    public SnakeHeadComponent getParent() {
        return parent;
    }
}
