package ru.nsu.valikov.petukhon.view;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getWorldProperties;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ru.nsu.valikov.petukhon.GameUtils;

/**
 * Draw right white panel.
 */
public class Artist {

    private static final int DEFAULT_FONT_SIZE = 22;
    private static final Color DEFAULT_COLOR = Color.BLACK;

    /**
     * Draw a Label and Value of it. Looks like high score.
     *
     * @param text         text at the label
     * @param shiftLabelX  label's shift toward game's board by x
     * @param shiftY       label's shift down top panel by y
     * @param shiftValueX  value's shift toward label by x
     * @param propertyName name of property that we want to show on the panel
     */
    public static void textWithBindedValue(String text, int shiftLabelX, int shiftY,
        int shiftValueX, String propertyName) {
        Text label = getUIFactoryService().newText(text, DEFAULT_COLOR, DEFAULT_FONT_SIZE);
        Text value = getUIFactoryService().newText("", DEFAULT_COLOR, DEFAULT_FONT_SIZE);
        label.setTranslateX(GameUtils.FIELD_WIDTH + shiftLabelX);
        label.setTranslateY(shiftY);
        value.setTranslateX(GameUtils.FIELD_WIDTH + shiftLabelX + shiftValueX);
        value.setTranslateY(shiftY);
        value.textProperty()
            .bind(getWorldProperties().intProperty(propertyName).asString());
        getGameScene().addUINodes(label, value);
    }

    /**
     * Set GAME OVER screen.
     *
     * @param isItEnd is it win of lose.
     */
    public static void gameOver(boolean isItEnd) {
        StringBuilder builder = new StringBuilder();
        builder.append("Game Over!\n\n");
        if (isItEnd) {
            builder.append("You have reached the end of the game!\n\n");
        }
        builder
            .append("Final level: ")
            .append(FXGL.geti("Level"));
        FXGL.getDialogService()
            .showMessageBox(builder.toString(), () -> FXGL.getGameController().gotoMainMenu());
    }

}
