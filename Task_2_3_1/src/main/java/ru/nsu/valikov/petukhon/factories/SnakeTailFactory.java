package ru.nsu.valikov.petukhon.factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.geometry.Point2D;
import ru.nsu.valikov.petukhon.GameUtils;
import ru.nsu.valikov.petukhon.PetukhonType;
import ru.nsu.valikov.petukhon.components.SnakeTailComponent;

/**
 * Snake's tail creator.
 */
public class SnakeTailFactory implements EntityFactory {

    public static final String NAME = "snake_tail";

    /**
     * Just a builder.
     *
     * @param data data to spawn
     * @return new snake's tail entity
     */
    @Spawns(NAME)
    public Entity newSnakeTail(SpawnData data) {
        final String tail = data.get("type");
        return FXGL.entityBuilder(data)
            .type(PetukhonType.SNAKE_TAIL)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(NAME
                    + '/'
                    + tail
                    + FactoryUtils.FORMAT_PNG,
                (double) (GameUtils.DEFAULT_WIDTH * 9) / 10,
                (double) (GameUtils.DEFAULT_HEIGHT * 9) / 10))
            .with(new SnakeTailComponent(data.get("parent")))
            .with(new AutoRotationComponent())
            .with(new CollidableComponent(true))
            .at(new Point2D(data.get("x"), data.get("y")))
            .zIndex(1)
            .buildAndAttach();
    }

}
