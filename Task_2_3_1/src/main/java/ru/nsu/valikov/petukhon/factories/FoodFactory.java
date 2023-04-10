package ru.nsu.valikov.petukhon.factories;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import java.util.SortedMap;
import java.util.TreeMap;
import ru.nsu.valikov.petukhon.GameUtils;
import ru.nsu.valikov.petukhon.PetukhonType;

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
            .type(PetukhonType.FOOD)
            .viewWithBBox(FXGL.getAssetLoader().loadTexture(NAME
                    + '/'
                    + IMAGE_NAMES.get(number)
                    + LOGO
                    + FactoryUtils.FORMAT_PNG,
                (double) (GameUtils.DEFAULT_WIDTH * 9) / 10,
                (double) (GameUtils.DEFAULT_HEIGHT * 9) / 10))
            .with(new CollidableComponent(true))
            .at(FactoryUtils.getRandomNonOverlappingPoint())
            .zIndex(1)
            .buildAndAttach();
    }
}