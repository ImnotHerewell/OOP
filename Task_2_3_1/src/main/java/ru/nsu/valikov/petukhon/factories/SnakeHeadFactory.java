package ru.nsu.valikov.petukhon.factories;

import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_HEIGHT;
import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_WIDTH;
import static ru.nsu.valikov.petukhon.factories.FactoryUtils.SPAWN_COEFFICIENT;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import ru.nsu.valikov.petukhon.components.AiComponent;
import ru.nsu.valikov.petukhon.components.SnakeHeadComponent;
import ru.nsu.valikov.petukhon.components.SnakeType;

/**
 * Enemy's head creator.
 */
public class SnakeHeadFactory implements EntityFactory {

    public static final String NAME = "snake_head";

    /**
     * Just a builder.
     *
     * @param data data to spawn
     * @return new evil snake head entity
     */
    @Spawns(NAME)
    public Entity newSnakeHead(SpawnData data) {
        final String head = "enemy";
        return FXGL.entityBuilder(data)
            .type(FactoryType.SNAKE_HEAD)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(NAME
                    + '/'
                    + head
                    + FactoryUtils.FORMAT_PNG,
                DEFAULT_WIDTH * SPAWN_COEFFICIENT,
                DEFAULT_HEIGHT * SPAWN_COEFFICIENT))
            .with(new CollidableComponent(true))
            .with(new AutoRotationComponent())
            .with(new AiComponent(data.get("type"), data.get("goal")))
            .with(new SnakeHeadComponent(SnakeType.ENEMY))
            .at(FactoryUtils.getRandomNonOverlappingPoint())
            .zIndex(1)
            .buildAndAttach();
    }
}

