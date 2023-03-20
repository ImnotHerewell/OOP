package ru.nsu.valikov.petukhon.factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import ru.nsu.valikov.petukhon.PetukhonType;

public class CellFactory implements EntityFactory {

    public static final String NAME = "cell";

    @Spawns(NAME)
    public Entity newCell(SpawnData data) {
        final String cell = "grey_cuted";
        return FXGL.entityBuilder(data).type(PetukhonType.CELL).viewWithBBox(FXGL.getAssetLoader()
                .loadTexture(
                    NAME + '/'
                        + cell
                        + FactoryUtils.FORMAT_PNG,
                    FactoryUtils.DEFAULT_WIDTH,
                    FactoryUtils.DEFAULT_HEIGHT))
            .buildAndAttach();
    }
}
