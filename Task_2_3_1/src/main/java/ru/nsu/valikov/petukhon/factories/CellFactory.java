package ru.nsu.valikov.petukhon.factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import ru.nsu.valikov.petukhon.GameUtils;
import ru.nsu.valikov.petukhon.PetukhonType;

/**
 * Creator of cells.
 */
public class CellFactory implements EntityFactory {

    public static final String NAME = "cell";

    /**
     * Just a builder.
     *
     * @param data data to spawn
     * @return new Cell Entity
     */
    @Spawns(NAME)
    public Entity newCell(SpawnData data) {
        final String cell = "grey";
        return FXGL.entityBuilder(data)
            .type(PetukhonType.CELL)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(NAME + '/'
                    + cell
                    + FactoryUtils.FORMAT_PNG,
                GameUtils.DEFAULT_WIDTH,
                GameUtils.DEFAULT_HEIGHT))
            .zIndex(0)
            .buildAndAttach();
    }
}
