package ru.nsu.valikov.petukhon.factories;

import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_HEIGHT;
import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_WIDTH;
import static ru.nsu.valikov.petukhon.factories.FactoryUtils.SPAWN_COEFFICIENT;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
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
            .type(FactoryType.SNAKE_TAIL)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(NAME
                    + '/'
                    + tail
                    + FactoryUtils.FORMAT_PNG,
                DEFAULT_WIDTH * SPAWN_COEFFICIENT,
                DEFAULT_HEIGHT * SPAWN_COEFFICIENT))
            .with(new SnakeTailComponent(data.get("parent")))
            .at(new Point2D(data.get("x"), data.get("y")))
            .zIndex(1)
            .buildAndAttach();
    }

}
