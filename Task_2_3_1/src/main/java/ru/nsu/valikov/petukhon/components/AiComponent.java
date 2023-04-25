package ru.nsu.valikov.petukhon.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

/**
 * Identifies AI snake entity.
 */
public class AiComponent extends Component {

    private final AiType aiType;
    private Entity goal;

    public AiComponent(AiType aiType, Entity goal) {
        this.aiType = aiType;
        this.goal = goal;
    }

    public void setGoal(Entity goal) {
        this.goal = goal;
    }

    public AiType getAiType() {
        return aiType;
    }

    @Override
    public void onUpdate(double tpf) {
        var sub = goal.getPosition().subtract(getEntity().getPosition());
        var component = getEntity().getComponent(SnakeHeadComponent.class);
        if (sub.getX() > 0) {
            component.setDirection(Direction.RIGHT);
        }
        if (sub.getX() < 0) {
            component.setDirection(Direction.LEFT);
        }
        if (sub.getY() < 0) {
            component.setDirection(Direction.UP);
        }
        if (sub.getY() > 0) {
            component.setDirection(Direction.DOWN);
        }
    }


}
