package ru.nsu.valikov.petukhon.factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import ru.nsu.valikov.petukhon.GameUtils;
import ru.nsu.valikov.petukhon.PetukhonType;


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
            .type(PetukhonType.DECORATION)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(NAME
                    + '/'
                    + decoration
                    + FactoryUtils.FORMAT_PNG,
                (double) (GameUtils.DEFAULT_WIDTH * 9) / 10,
                (double) (GameUtils.DEFAULT_HEIGHT * 9) / 10))
            .with(new CollidableComponent(true))
            .at(FactoryUtils.getRandomNonOverlappingPoint())
            .zIndex(1)
            .buildAndAttach();
    }
}