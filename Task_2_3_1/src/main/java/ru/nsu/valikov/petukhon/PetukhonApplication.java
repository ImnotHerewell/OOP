package ru.nsu.valikov.petukhon;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsWorld;
import java.util.Map;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ru.nsu.valikov.petukhon.components.Direction;
import ru.nsu.valikov.petukhon.components.SnakeHeadComponent;
import ru.nsu.valikov.petukhon.factories.CellFactory;
import ru.nsu.valikov.petukhon.factories.DecorationFactory;
import ru.nsu.valikov.petukhon.factories.FactoryUtils;
import ru.nsu.valikov.petukhon.factories.FoodFactory;
import ru.nsu.valikov.petukhon.factories.SnakeHeadFactory;
import ru.nsu.valikov.petukhon.factories.SnakeTailFactory;
import ru.nsu.valikov.petukhon.handlers.Collisions;
import ru.nsu.valikov.petukhon.view.Artist;

/**
 * Main Game Class.
 */
public class PetukhonApplication extends GameApplication {

    private Stage stage;
    private Entity player;

    public static void main(String[] args) {
        launch(args);
    }

    public Entity getPlayer() {
        return player;
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("ScoreToWin", 10);
        vars.put("Level", 0);
        vars.put("CurrentScore", 1);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(GameUtils.FIELD_WIDTH + GameUtils.SCORE_WIDTH);
        settings.setHeight(GameUtils.FIELD_HEIGHT);
        settings.setTicksPerSecond(20);
        settings.setTitle("Petukhon!");
        settings.setVersion("09.04.2023");
    }

    @Override
    protected void initInput() {
        FXGL.onKeyDown(KeyCode.W,
            () -> {
                player.getComponent(SnakeHeadComponent.class).setDirection(Direction.UP);
            });
        FXGL.onKeyDown(KeyCode.A,
            () -> {
                player.getComponent(SnakeHeadComponent.class).setDirection(Direction.LEFT);
            });

        FXGL.onKeyDown(KeyCode.S,
            () -> {
                player.getComponent(SnakeHeadComponent.class).setDirection(Direction.DOWN);
            });

        FXGL.onKeyDown(KeyCode.D,
            () -> {
                player.getComponent(SnakeHeadComponent.class).setDirection(Direction.RIGHT);
            });

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new DecorationFactory());
        getGameWorld().addEntityFactory(new SnakeHeadFactory());
        getGameWorld().addEntityFactory(new FoodFactory());
        getGameWorld().addEntityFactory(new CellFactory());
        getGameWorld().addEntityFactory(new SnakeTailFactory());
        getGameWorld().spawn(FoodFactory.NAME);
        getGameWorld().spawn(DecorationFactory.NAME);
        getGameWorld().spawn(DecorationFactory.NAME);
        getGameWorld().spawn(DecorationFactory.NAME);
        getGameWorld().spawn(DecorationFactory.NAME);
        //getGameWorld().spawn(SnakeHeadFactory.NAME);
        player = FactoryUtils.createThePlayer();
        //как синглтон надо
    }

    @Override
    protected void initPhysics() {
        final PhysicsWorld world = FXGL.getPhysicsWorld();
        world.addCollisionHandler(
            Collisions.clashBetweenHeadAndFood());
        world.addCollisionHandler(
            Collisions.clashBetweenHeadAndDecoration());
        world.addCollisionHandler(
            Collisions.clashBetweenHeadAndTail());
    }

    @Override
    protected void initUI() {
        Artist.textWithBindedValue("Current score", 20, 20, 150, "CurrentScore");
        Artist.textWithBindedValue("Score to win", 20, 50, 150, "ScoreToWin");
        Artist.textWithBindedValue("Level", 20, 80, 150, "Level");

        for (int coordinateX = 0; coordinateX <= GameUtils.FIELD_WIDTH;
            coordinateX += GameUtils.DEFAULT_WIDTH) {
            for (int coordinateY = 0; coordinateY <= GameUtils.FIELD_HEIGHT;
                coordinateY += GameUtils.DEFAULT_HEIGHT) {
                getGameWorld().spawn(CellFactory.NAME, coordinateX, coordinateY);
            }
        }
    }
}