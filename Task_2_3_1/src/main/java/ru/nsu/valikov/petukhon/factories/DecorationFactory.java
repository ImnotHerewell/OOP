package ru.nsu.valikov.petukhon.factories;

import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_HEIGHT;
import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_WIDTH;
import static ru.nsu.valikov.petukhon.factories.FactoryUtils.SPAWN_COEFFICIENT;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;


/**
 * Creator of decorations.
 */
public class DecorationFactory implements EntityFactory {

    public static final String NAME = "decoration";

    /**
     * Just a builder.
     *
     * @param data data to spawn
     * @return new Decoration Entity
     */
    @Spawns(NAME)
    public Entity newDecoration(SpawnData data) {
        final String decoration = "X_inside_the_box";
        return FXGL.entityBuilder(data)
            .type(FactoryType.DECORATION)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(NAME
                    + '/'
                    + decoration
                    + FactoryUtils.FORMAT_PNG,
                DEFAULT_WIDTH * SPAWN_COEFFICIENT,
                DEFAULT_HEIGHT * SPAWN_COEFFICIENT))
            .with(new CollidableComponent(true))
            .at(data.get("x"), data.get("y"))
            .zIndex(1)
            .build();
    }
}
