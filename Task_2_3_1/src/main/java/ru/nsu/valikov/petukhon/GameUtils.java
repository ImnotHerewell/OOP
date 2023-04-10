package ru.nsu.valikov.petukhon;

import static com.almasb.fxgl.core.math.FXGLMath.abs;

import javafx.geometry.Point2D;

/**
 * Contains common data for all game's components.
 */
public class GameUtils {

    public static final int DEFAULT_HEIGHT = 20;
    public static final int DEFAULT_WIDTH = 20;

    public static final int FIELD_HEIGHT = 700;
    public static final int FIELD_WIDTH = 700;
    public static final int SCORE_WIDTH = 200;

    /**
     * When a snake moves out of the game's board, this method generates a new coordinate to
     * translate for.
     *
     * @param point current point (may be wrong)
     * @return correct point
     */
    public static Point2D translateWithTeleport(Point2D point) {
        double x = point.getX();
        double y = point.getY();
        if (!isInsideBounds(x, FIELD_WIDTH)) {
            x = abs(FIELD_WIDTH - (x == FIELD_WIDTH ? 0 : DEFAULT_WIDTH) - x);
        }
        if (!isInsideBounds(y, FIELD_HEIGHT)) {
            y = abs(FIELD_HEIGHT - (y == FIELD_HEIGHT ? 0 : DEFAULT_HEIGHT) - y);
        }
        return new Point2D(x, y);
    }

    /**
     * Check point on nesting into bounds.
     *
     * @param coordinate coordinate to check
     * @param upperBound bounds
     * @return true if point inside bounds else false
     */
    public static boolean isInsideBounds(double coordinate, double upperBound) {
        return coordinate >= 0 && coordinate < upperBound;
    }
}
