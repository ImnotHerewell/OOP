package ru.nsu.valikov.petukhon.factories;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

import com.almasb.fxgl.core.math.FXGLMath;
import java.util.function.Supplier;
import javafx.geometry.Point2D;
import ru.nsu.valikov.petukhon.GameUtils;

public class FactoryUtils {

    public static final int DEFAULT_HEIGHT = 20;
    public static final int DEFAULT_WIDTH = 20;
    public static final String FORMAT_PNG = ".png";


    private FactoryUtils() {
        throw new AssertionError();
    }

    public static Point2D getRandomNonOverlappingPoint() {
        Supplier<Integer> getRandomY = () -> (FXGLMath.random(0, GameUtils.FIELD_WIDTH)
            / DEFAULT_WIDTH * DEFAULT_WIDTH);
        Supplier<Integer> getRandomX = () -> (FXGLMath.random(0, GameUtils.FIELD_HEIGHT)
            / DEFAULT_HEIGHT * DEFAULT_HEIGHT);
        int coordinateX = getRandomX.get();
        int coordinateY = getRandomY.get();
        while (getGameWorld().getEntitiesAt(new Point2D(coordinateX, coordinateY)).size() > 1) {
            coordinateX = getRandomX.get();
            coordinateY = getRandomY.get();
        }
        return new Point2D(coordinateX, coordinateY);
    }

}
