package ru.nsu.valikov.petukhon.factories;

import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_HEIGHT;
import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_WIDTH;
import static ru.nsu.valikov.petukhon.factories.FactoryUtils.SPAWN_COEFFICIENT;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Food creator.
 */
public class FoodFactory implements EntityFactory {

    public static final String NAME = "food";

    private static final SortedMap<Integer, String> IMAGE_NAMES = new TreeMap<>() {
        {
            put(0, "blank");
            put(this.lastKey() + 1, "C++");
            put(this.lastKey() + 1, "Rust");
            put(this.lastKey() + 1, "Java");
            put(this.lastKey() + 1, "Haskell");
            put(this.lastKey() + 1, "Smalltalk");
        }
    };
    private static final String LOGO = "_logo";


    /**
     * Just a builder.
     *
     * @param data data to spawn
     * @return new food entity
     */
    @Spawns(NAME)
    public Entity newFood(SpawnData data) {
        final int number = FXGLMath.random(1, IMAGE_NAMES.size() - 1);
        return FXGL.entityBuilder(data)
            .type(FactoryType.FOOD)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(NAME
                    + '/'
                    + IMAGE_NAMES.get(number)
                    + LOGO
                    + FactoryUtils.FORMAT_PNG,
                DEFAULT_WIDTH * SPAWN_COEFFICIENT,
                DEFAULT_HEIGHT * SPAWN_COEFFICIENT))
            .with(new CollidableComponent(true))
            .at(FactoryUtils.getRandomNonOverlappingPoint())
            .zIndex(1)
            .buildAndAttach();
    }
}
