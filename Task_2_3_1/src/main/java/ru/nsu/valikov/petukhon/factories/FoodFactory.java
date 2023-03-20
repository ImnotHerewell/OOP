package ru.nsu.valikov.petukhon.factories;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import ru.nsu.valikov.petukhon.PetukhonType;

public class FoodFactory implements EntityFactory {

    public static final String NAME = "food";

    private static final int NUMBER_OF_TYPES = 5;

    private static final String BLANK = "blank";
    private static final String HASKELL = "Haskell";
    private static final String C_PLUS_PLUS = "C++";
    private static final String JAVA = "Java";
    private static final String RUST = "Rust";
    private static final String SMALLTALK = "Smalltalk";

    private static final String LOGO = "_logo";


    private static final int HASKELL_ID = 0;
    private static final int JAVA_ID = 1;
    private static final int C_PLUS_PLUS_ID = 2;
    private static final int RUST_ID = 3;
    private static final int SMALLTALK_ID = 4;

    @Spawns(NAME)
    public Entity newFood(SpawnData data) {
        final int number = FXGLMath.random(0, NUMBER_OF_TYPES - 1);
        return FXGL.entityBuilder(data).type(PetukhonType.FOOD).viewWithBBox(
                FXGL.getAssetLoader().loadTexture(NAME + '/'
                        + switch (number) {
                        case HASKELL_ID -> HASKELL;
                        case JAVA_ID -> JAVA;
                        case C_PLUS_PLUS_ID -> C_PLUS_PLUS;
                        case RUST_ID -> RUST;
                        case SMALLTALK_ID -> SMALLTALK;
                        default -> BLANK;
                    } + LOGO + FactoryUtils.FORMAT_PNG,
                    FactoryUtils.DEFAULT_WIDTH,
                    FactoryUtils.DEFAULT_HEIGHT)).with(new CollidableComponent(true))
            .at(FactoryUtils.getRandomNonOverlappingPoint())
            .buildAndAttach();
    }
}
