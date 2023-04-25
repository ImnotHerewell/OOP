package ru.nsu.valikov.petukhon;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getWorldProperties;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsWorld;
import java.util.Map;
import javafx.scene.input.KeyCode;
import ru.nsu.valikov.petukhon.components.Direction;
import ru.nsu.valikov.petukhon.components.SnakeHeadComponent;
import ru.nsu.valikov.petukhon.factories.CellFactory;
import ru.nsu.valikov.petukhon.factories.DecorationFactory;
import ru.nsu.valikov.petukhon.factories.FoodFactory;
import ru.nsu.valikov.petukhon.factories.SnakeHeadFactory;
import ru.nsu.valikov.petukhon.factories.SnakeTailFactory;
import ru.nsu.valikov.petukhon.handlers.Collisions;
import ru.nsu.valikov.petukhon.view.Artist;

/**
 * Main Game Class.
 */
public class PetukhonApplication extends GameApplication {

    private static Entity player;
    private static PropertyMap properties = new PropertyMap();

    public static void main(String[] args) {
        launch(args);
    }

    public static void setPlayer(Entity newPlayer) {
        player = newPlayer;
    }

    public static void setIntProperty(String key, int value) {
        properties.setValue(key, value);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("ScoreToWin", properties.getInt("ScoreToWin"));
        vars.put("Level", 1);
        vars.put("CurrentScore", 0);

    }


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(GameUtils.FIELD_WIDTH + GameUtils.SCORE_WIDTH);
        settings.setHeight(GameUtils.FIELD_HEIGHT);
        settings.setTicksPerSecond(60);
        settings.setTitle("Petukhon!");
        settings.setVersion("25.04.2023");
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                //return new SimpleGameMenu();
                return new PetukhonMainMenu();
            }
        });
    }

    @Override
    protected void initInput() {
        FXGL.onKeyDown(KeyCode.W, "UP",
            () -> player.getComponent(SnakeHeadComponent.class).setDirection(Direction.UP));
        FXGL.onKeyDown(KeyCode.A, "LEFT",
            () -> player.getComponent(SnakeHeadComponent.class).setDirection(Direction.LEFT));
        FXGL.onKeyDown(KeyCode.S, "DOWN",
            () -> player.getComponent(SnakeHeadComponent.class).setDirection(Direction.DOWN));
        FXGL.onKeyDown(KeyCode.D, "RIGHT",
            () -> player.getComponent(SnakeHeadComponent.class).setDirection(Direction.RIGHT));

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new DecorationFactory());
        getGameWorld().addEntityFactory(new SnakeHeadFactory());
        getGameWorld().addEntityFactory(new FoodFactory());
        getGameWorld().addEntityFactory(new CellFactory());
        getGameWorld().addEntityFactory(new SnakeTailFactory());
        GameUtils.setDefaultLevelFromFile(getWorldProperties().getInt("Level"));

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
        world.addCollisionHandler(Collisions.clashBetweenHeadAndHead());
    }

    @Override
    protected void initUI() {
        Artist.textWithBindedValue("Current score", 10, 20, 150, "CurrentScore");
        Artist.textWithBindedValue("Score to win", 10, 50, 150, "ScoreToWin");
        Artist.textWithBindedValue("Level", 10, 80, 150, "Level");
    }
}