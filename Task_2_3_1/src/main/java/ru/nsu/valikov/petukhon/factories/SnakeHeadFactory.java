package ru.nsu.valikov.petukhon.factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import ru.nsu.valikov.petukhon.GameUtils;
import ru.nsu.valikov.petukhon.PetukhonType;
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
            .type(PetukhonType.SNAKE_HEAD)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(NAME
                    + '/'
                    + head
                    + FactoryUtils.FORMAT_PNG,
                (double) (GameUtils.DEFAULT_WIDTH * 9) / 10,
                (double) (GameUtils.DEFAULT_HEIGHT * 9) / 10))
            .with(new CollidableComponent(true))
            .with(new SnakeHeadComponent(SnakeType.ENEMY))
            .with(new AutoRotationComponent())
            .at(FactoryUtils.getRandomNonOverlappingPoint())
            .zIndex(1)
            .buildAndAttach();
    }
}

