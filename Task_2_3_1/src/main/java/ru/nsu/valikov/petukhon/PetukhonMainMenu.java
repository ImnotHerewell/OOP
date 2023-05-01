package ru.nsu.valikov.petukhon;


import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.MenuType;
import java.util.List;
import javafx.collections.FXCollections;

/**
 * My main menu.
 */
public class PetukhonMainMenu extends FXGLDefaultMenu {

    public PetukhonMainMenu() {
        super(MenuType.MAIN_MENU);

    }

    @Override
    public void onCreate() {
        createLengthCheckBox();
        createFoodBox();
    }

    private void createLengthCheckBox() {
        var winBox = getUIFactoryService().newButton("LENGTH FOR WIN");
        winBox.setLayoutX(50);
        if (!PetukhonApplication.hasIntProperty("ScoreToWin")) {
            PetukhonApplication.setIntProperty("ScoreToWin", 10);
        }
        winBox.setOnAction(event -> {
            var node = getUIFactoryService().newChoiceBox(
                    FXCollections.observableList(List.of(5, 10, 15, 20)));
            node.setOnAction(nodeEvent -> {
                PetukhonApplication.setIntProperty("ScoreToWin", node.getValue());
            });
            node.setLayoutX(50 * 6);
            node.setLayoutY(getAppHeight() / 1.5);
            getContentRoot().getChildren().add(node);
        });
        winBox.setLayoutY(getAppHeight() / 1.5);
        getContentRoot().getChildren().add(winBox);
    }

    private void createFoodBox() {
        var winBox = getUIFactoryService().newButton("FOOD COUNT");
        winBox.setLayoutX(50);
        if (!PetukhonApplication.hasIntProperty("Food")) {
            PetukhonApplication.setIntProperty("Food", 1);
        }
        winBox.setOnAction(event -> {
            var node = getUIFactoryService().newChoiceBox(
                    FXCollections.observableList(List.of(1, 2, 3, 4, 5)));
            node.setOnAction(nodeEvent -> {
                PetukhonApplication.setIntProperty("Food", node.getValue());
            });
            node.setLayoutX(50 * 6);
            node.setLayoutY(getAppHeight() / 1.3);
            getContentRoot().getChildren().add(node);
        });
        winBox.setLayoutY(getAppHeight() / 1.3);
        getContentRoot().getChildren().add(winBox);
    }
}