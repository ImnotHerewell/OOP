package ru.nsu.valikov.petukhon;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import java.util.Map;
import javafx.scene.input.KeyCode;
import lombok.Getter;
import ru.nsu.valikov.petukhon.factories.CellFactory;
import ru.nsu.valikov.petukhon.factories.DecorationFactory;
import ru.nsu.valikov.petukhon.factories.FactoryUtils;
import ru.nsu.valikov.petukhon.factories.FoodFactory;
import ru.nsu.valikov.petukhon.factories.SnakeFactory;

public class PetukhonApplication extends GameApplication {

    @Getter
    private Entity player;

    public static void main(String[] args) {
        launch(args);
    }

    //    private Parent createContent() {
    //        TextField input = new TextField();
    //        Text output = new Text();
    //        Button button = new Button("Press");
    //        button.setOnAction(e -> {
    //            output.setText(input.getText());
    //        });
    //        VBox root = new VBox(input, button, output);
    //        root.setPrefSize(900, 1600);
    //        return root;
    //    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(GameUtils.FIELD_WIDTH);
        settings.setHeight(GameUtils.FIELD_HEIGHT);
        settings.setTitle("Petukhon!");
        settings.setVersion("01.03.2023");
    }

    @Override
    protected void initInput() {
        //        FXGL.getInput().addAction(new UserAction("Right") {
        //            @Override
        //            protected void onAction() {
        //                player.getComponent(AnimationComponent.class).moveRight();
        //            }
        //        }, KeyCode.D);

        //        FXGL.getInput().addAction(new UserAction("Left") {
        //            @Override
        //            protected void onAction() {
        //                player.getComponent(AnimationComponent.class).moveLeft();
        //            }
        //        }, KeyCode.A);
        FXGL.onKeyDown(KeyCode.F, () -> {
            FXGL.play("убил сам себя.wav");
        });
        FXGL.onKey(KeyCode.W, () -> {
            player.translateY(-1);
            FXGL.inc("SnakeCoordY", +1);
        });

        FXGL.onKey(KeyCode.A, () -> {
            player.translateX(-1);
            FXGL.inc("SnakeCoordX", -1);
        });

        FXGL.onKey(KeyCode.S, () -> {
            player.translateY(1);
            FXGL.inc("SnakeCoordY", -1);
        });

        FXGL.onKey(KeyCode.D, () -> {
            player.translateX(1);
            FXGL.inc("SnakeCoordX", +1);
        });

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("SnakeIsCreated", 0);
        vars.put("SnakeCoordX", 0);
        vars.put("SnakeCoordY", 0);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new DecorationFactory());
        getGameWorld().addEntityFactory(new SnakeFactory());
        getGameWorld().addEntityFactory(new FoodFactory());
        getGameWorld().addEntityFactory(new CellFactory());
        getGameWorld().spawn(FoodFactory.NAME);
        player = FXGL.entityBuilder().type(PetukhonType.SNAKE).viewWithBBox(FXGL.getAssetLoader()
                .loadTexture("snake"
                        +
                        "/enemy"
                        +
                        ".png",
                    FactoryUtils.DEFAULT_WIDTH,
                    FactoryUtils.DEFAULT_HEIGHT))
            .at(300, 300).with(new CollidableComponent(true)).buildAndAttach();
        //        FXGL.entityBuilder().type(PetukhonzType.FOOD).at(500, 200).viewWithBBox("brick2
        //        .png").with(
        //                new CollidableComponent(true)).buildAndAttach();
        //        player.addComponent(new MoveSpeedComponent()); есть вары в мапе
    }

    /**
     *
     */
    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(
            new CollisionHandler(PetukhonType.SNAKE, PetukhonType.FOOD) {

                @Override
                protected void onCollisionBegin(Entity snake, Entity food) {
                    food.removeFromWorld();
                    getGameWorld().spawn(FoodFactory.NAME);
                    //                        if (snake.hasComponent())
                }
            });
    }

    /**
     * левели здесь делаем
     */
    @Override
    protected void initUI() {
        for (int coordinateX = 0; coordinateX <= GameUtils.FIELD_WIDTH;
            coordinateX += FactoryUtils.DEFAULT_WIDTH) {
            for (int coordinateY = 0; coordinateY <= GameUtils.FIELD_HEIGHT;
                coordinateY += FactoryUtils.DEFAULT_HEIGHT) {
                getGameWorld().spawn(CellFactory.NAME, coordinateX + 1, coordinateY + 1);
            }
        }
        //        var brickTexture = FXGL.getAssetLoader().loadTexture("brick.png");
        //        brickTexture.setTranslateX(50);
        //        brickTexture.setTranslateY(450);
        //        FXGL.getGameScene().addUINode(brickTexture);
    }
}