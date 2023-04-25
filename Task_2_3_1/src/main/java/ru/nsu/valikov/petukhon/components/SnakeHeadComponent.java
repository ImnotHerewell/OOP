package ru.nsu.valikov.petukhon.components;

import static com.almasb.fxgl.core.math.FXGLMath.abs;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getWorldProperties;
import static com.almasb.fxgl.dsl.FXGLForKtKt.runOnce;
import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_HEIGHT;
import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_WIDTH;
import static ru.nsu.valikov.petukhon.components.Direction.DOWN;
import static ru.nsu.valikov.petukhon.components.Direction.FROZEN;
import static ru.nsu.valikov.petukhon.components.Direction.LEFT;
import static ru.nsu.valikov.petukhon.components.Direction.RIGHT;
import static ru.nsu.valikov.petukhon.components.Direction.UP;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import kotlin.NotImplementedError;
import ru.nsu.valikov.petukhon.GameUtils;
import ru.nsu.valikov.petukhon.factories.SnakeTailFactory;


/**
 * Identifies snake's head and its opportunities.
 */
public class SnakeHeadComponent extends Component {

    private final SnakeType type;
    private final Deque<Entity> tailParts;
    private final Queue<Direction> moveQueue;
    private final List<Point2D> partsCoordinates;
    private int speed;
    private Direction direction;

    /**
     * Constructs a head component with given type.
     *
     * @param type ENEMY or PLAYER.
     */
    public SnakeHeadComponent(SnakeType type) {
        this.type = type;
        tailParts = new ArrayDeque<>();
        moveQueue = new ArrayDeque<>();
        partsCoordinates = new ArrayList<>();
    }

    /**
     * Set a new direction for a snake.
     *
     * @param direction to set
     */
    public void setDirection(Direction direction) {
        var currentDirection = this.direction;
        if ((currentDirection.equals(FROZEN))
            || ((currentDirection.equals(LEFT) || currentDirection.equals(RIGHT))
            && (direction.equals(UP) || direction.equals(DOWN)))
            || ((currentDirection.equals(UP) || currentDirection.equals(DOWN))
            && (direction.equals(LEFT) || direction.equals(RIGHT)))) {
            moveQueue.add(direction);
        }
    }

    public SnakeType getType() {
        return type;
    }

    public Deque<Entity> getTailParts() {
        return tailParts;
    }

    @Override
    public void onAdded() {
        direction = FROZEN;
        partsCoordinates.add(entity.getPosition());
        speed = 4;
        if (entity.hasComponent(AiComponent.class)) {
            speed /= 2;
        }
    }

    private void entityTranslator() {
        switch (direction) {
            case UP -> entity.translateY(-speed);
            case DOWN -> entity.translateY(speed);
            case RIGHT -> entity.translateX(speed);
            case LEFT -> entity.translateX(-speed);
            case FROZEN -> {
            }
            default -> throw new NotImplementedError();
        }
    }

    private void directionChanger() {
        if (entity.getX() % DEFAULT_WIDTH == 0
            && entity.getY() % GameUtils.DEFAULT_HEIGHT == 0) {
            while (!moveQueue.isEmpty()) {
                direction = moveQueue.poll();
            }
        }
    }

    private void tailMover() {
        int counter = 0;
        var oldPosition = entity.getPosition();
        boolean flag = false;
        for (Entity part : tailParts) {
            if ((abs(partsCoordinates.get(counter).subtract(oldPosition).getX()) >= DEFAULT_WIDTH)
                || (abs(partsCoordinates.get(counter).subtract(oldPosition).getY())
                >= DEFAULT_HEIGHT)) {
                part.setPosition(partsCoordinates.get(counter));
                partsCoordinates.set(counter++, oldPosition);
                oldPosition = part.getPosition();
                flag = true;
                continue;
            }
            flag = false;
            break;
        }
        if (flag) {
            partsCoordinates.set(counter, oldPosition);
        }
    }

    @Override
    public void onUpdate(double tpf) {
        entityTranslator();
        directionChanger();
        entity.setPosition(GameUtils.translateWithTeleport(entity.getPosition()));
        tailMover();
    }

    /**
     * Eat food and increase body length.
     */
    public void eat() {
        addNewPart(generateSpawnData());
        if (type == SnakeType.PLAYER) {
            increaseEatProperties();
        }
    }

    private SpawnData generateSpawnData() {
        final SpawnData data = new SpawnData();
        final double x =
            tailParts.isEmpty() ? entity.getX()
                : partsCoordinates.get(partsCoordinates.size() - 1).getX();
        final double y = tailParts.isEmpty() ? entity.getY()
            : partsCoordinates.get(partsCoordinates.size() - 1).getY();
        data.put("type", type.name().toLowerCase());
        data.put("x", x);
        data.put("y", y);
        data.put("parent", this);
        return data;
    }

    private void addNewPart(SpawnData data) {
        var tail = getGameWorld().spawn(SnakeTailFactory.NAME, data);
        runOnce(() -> {
            tail.addComponent(new CollidableComponent(true));
            return null;
        }, new Duration(1000));
        partsCoordinates.add(tail.getPosition());
        tailParts.addLast(tail);
    }

    private void increaseEatProperties() {
        var properties = getWorldProperties();
        properties.setValue("CurrentScore", tailParts.size());
        if (Objects.equals(properties.getInt("CurrentScore"),
            properties.getInt("ScoreToWin"))) {
            properties.setValue("CurrentScore", 0);
            properties.increment("Level", 1);
            GameUtils.setDefaultLevelFromFile(properties.getInt("Level"));
        }
    }
}
