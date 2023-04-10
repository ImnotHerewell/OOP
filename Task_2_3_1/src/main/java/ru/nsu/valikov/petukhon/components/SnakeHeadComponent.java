package ru.nsu.valikov.petukhon.components;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getWorldProperties;
import static ru.nsu.valikov.petukhon.components.Direction.DOWN;
import static ru.nsu.valikov.petukhon.components.Direction.LEFT;
import static ru.nsu.valikov.petukhon.components.Direction.RIGHT;
import static ru.nsu.valikov.petukhon.components.Direction.UP;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import java.util.ArrayDeque;
import java.util.Deque;
import javafx.geometry.Point2D;
import kotlin.NotImplementedError;
import ru.nsu.valikov.petukhon.GameUtils;
import ru.nsu.valikov.petukhon.factories.SnakeTailFactory;


/**
 * Identifies snake's head and its opportunities.
 */
public final class SnakeHeadComponent extends Component {

    private static final int SPEED = 20;
    private final SnakeType type;
    private final Deque<Entity> tailParts;
    private Direction direction;

    public SnakeHeadComponent(SnakeType type) {
        this.type = type;
        this.tailParts = new ArrayDeque<>();
    }

    public SnakeType getType() {
        return type;
    }

    public Deque<Entity> getTailParts() {
        return tailParts;
    }

    @Override
    public void onAdded() {
        direction = RIGHT;
    }

    @Override
    public void onUpdate(double tpf) {
        Point2D oldPosition = entity.getPosition();
        switch (direction) {
            case UP -> entity.translateY(-SPEED);
            case DOWN -> entity.translateY(SPEED);
            case RIGHT -> entity.translateX(SPEED);
            case LEFT -> entity.translateX(-SPEED);
            default -> throw new NotImplementedError();
        }
        entity.setPosition(GameUtils.translateWithTeleport(entity.getPosition()));
        for (Entity part : tailParts) {
            Point2D currentPosition = part.getPosition();
            part.setPosition(oldPosition);
            oldPosition = currentPosition;
        }
    }

    /**
     * Set new direction for a snake.
     *
     * @param direction to set
     */
    public void setDirection(Direction direction) {
        if ((this.direction.equals(LEFT) || this.direction.equals(RIGHT))
            && (direction.equals(UP) || direction.equals(DOWN))) {
            this.direction = direction;
        } else if ((this.direction.equals(UP) || this.direction.equals(DOWN))
            && (direction.equals(LEFT) || direction.equals(RIGHT))) {
            this.direction = direction;
        }
    }

    /**
     * Eat food and increase body length.
     */
    public void eat() {
        final SpawnData data = new SpawnData();
        final double x = tailParts.isEmpty() ? entity.getX() : tailParts.getLast().getX();
        final double y = tailParts.isEmpty() ? entity.getY() : tailParts.getLast().getY();
        data.put("type", type.name().toLowerCase());
        data.put("x", x);
        data.put("y", y);
        data.put("parent", this);
        tailParts.add(
            getGameWorld().spawn(SnakeTailFactory.NAME, data));
        if (entity.getComponent(SnakeHeadComponent.class).type == SnakeType.PLAYER) {
            var properties = getWorldProperties();
            properties.setValue("CurrentScore", tailParts.size());
            if (properties.getInt("CurrentScore") == 10) {
                System.exit(0);
            }
        }
    }
}
