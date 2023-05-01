package ru.nsu.valikov.petukhon.factories;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_HEIGHT;
import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_WIDTH;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import java.util.function.Supplier;
import javafx.geometry.Point2D;
import ru.nsu.valikov.petukhon.GameUtils;
import ru.nsu.valikov.petukhon.components.SnakeHeadComponent;
import ru.nsu.valikov.petukhon.components.SnakeType;

/**
 * Utils for sharing common data between factories.
 */
public class FactoryUtils {

    public static final String FORMAT_PNG = ".png";
    public static final double SPAWN_COEFFICIENT = 9 / 10.;

    private FactoryUtils() {
        throw new AssertionError();
    }

    /**
     * Get random point.
     *
     * @return point where we can place any Entity.
     */
    public static Point2D getRandomNonOverlappingPoint() {
        Supplier<Integer> getRandomY = () -> (
            FXGLMath.random(0, GameUtils.FIELD_WIDTH / DEFAULT_WIDTH - 1) * DEFAULT_WIDTH);
        Supplier<Integer> getRandomX = () -> (
            FXGLMath.random(0, GameUtils.FIELD_HEIGHT / DEFAULT_HEIGHT - 1) * DEFAULT_HEIGHT);
        int coordinateX = getRandomX.get();
        int coordinateY = getRandomY.get();
        while (getGameWorld().getEntitiesAt(new Point2D(coordinateX, coordinateY)).size() > 1) {
            coordinateX = getRandomX.get();
            coordinateY = getRandomY.get();
        }
        return new Point2D(coordinateX, coordinateY);
    }


    /**
     * Should be like Singleton, but instead of that we just separate player's creation from
     * factories.
     *
     * @return player's instance
     */
    public static Entity createThePlayer() {
        return FXGL.entityBuilder()
            .type(FactoryType.SNAKE_HEAD)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(SnakeHeadFactory.NAME
                    +
                    "/player"
                    +
                    FactoryUtils.FORMAT_PNG,
                (double) (GameUtils.DEFAULT_WIDTH * 9) / 10,
                (double) (GameUtils.DEFAULT_HEIGHT * 9) / 10))
            .at(300, 300)
            .with(new CollidableComponent(true))
            .with(new AutoRotationComponent())
            .with(new SnakeHeadComponent(SnakeType.PLAYER))
            .zIndex(1)
            .build();
    }
}
