package ru.nsu.valikov.petukhon;

import static com.almasb.fxgl.core.math.FXGLMath.abs;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static ru.nsu.valikov.petukhon.PetukhonApplication.setPlayer;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.level.Level;
import javafx.geometry.Point2D;
import ru.nsu.valikov.petukhon.components.AiType;
import ru.nsu.valikov.petukhon.factories.CellFactory;
import ru.nsu.valikov.petukhon.factories.FactoryUtils;
import ru.nsu.valikov.petukhon.view.Artist;

/**
 * Contains common data for all game's components.
 */
public class GameUtils {

    public static final int DEFAULT_HEIGHT = 20;
    public static final int DEFAULT_WIDTH = 20;

    public static final int FIELD_HEIGHT = 700;
    public static final int FIELD_WIDTH = 700;
    public static final int SCORE_WIDTH = 200;
    public static final int LEVELS_COUNT = 5;
    private static final LevelLoader levelLoader = new LevelLoader();

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

    /**
     * Set level from *.txt file. Format: - d = decoration - 0 = nothing
     *
     * @param level name, where name.txt - is filename
     */
    public static void setDefaultLevelFromFile(int level) {
        if (level >= LEVELS_COUNT) {
            Artist.gameOver(true);
        }
        var entities = levelLoader.takeEntities(String.valueOf(level));
        var player = FactoryUtils.createThePlayer();
        entities.add(player);
        getGameWorld().setLevel(new Level(700, 700, entities));
        drawBoard();
        spawnEstimatedEntities(player);
    }

    private static void drawBoard() {
        for (int coordinateX = 0; coordinateX < GameUtils.FIELD_WIDTH;
            coordinateX += GameUtils.DEFAULT_WIDTH) {
            for (int coordinateY = 0; coordinateY <= GameUtils.FIELD_HEIGHT;
                coordinateY += GameUtils.DEFAULT_HEIGHT) {
                getGameWorld().spawn(CellFactory.NAME, coordinateX, coordinateY);
            }
        }
    }

    private static void spawnEstimatedEntities(Entity player) {
        setPlayer(player);
        var food = getGameWorld().spawn("food");
        var data = new SpawnData() {
            {
                put("type", AiType.EAT);
                put("goal", food);
            }
        };
        getGameWorld().spawn("snake_head", data);
        data.put("type", AiType.KILL);
        data.put("goal", player);
        getGameWorld().spawn("snake_head", data);
    }
}
