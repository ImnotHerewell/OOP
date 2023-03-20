package ru.nsu.valikov.petukhon;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.TransformComponent;

//https://github.com/AlmasB/FXGL/wiki/Entity-Component-%28FXGL-11%29
public class PlayerComponent extends Component {

    // note that this component is injected automatically
    private TransformComponent position;

    private double speed = 0;

    @Override
    public void onUpdate(double tpf) {
        speed = tpf * 60;
    }

    public void up() {
        position.translateY(-5 * speed);
    }

    public void down() {
        position.translateY(5 * speed);
    }

    public void left() {
        position.translateX(-5 * speed);
    }

    public void right() {
        position.translateX(5 * speed);
    }
}
