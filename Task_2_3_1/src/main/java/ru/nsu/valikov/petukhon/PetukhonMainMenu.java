package ru.nsu.valikov.petukhon;


import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.MenuType;
import java.util.List;
import javafx.collections.FXCollections;

/**
 * @author LeeWyatt 游戏的主菜单场景
 */
public class PetukhonMainMenu extends FXGLDefaultMenu {

    public PetukhonMainMenu() {
        super(MenuType.MAIN_MENU);

    }

    @Override
    public void onCreate() {
        createLengthCheckBox();
//        createSizeCheckBox();
    }

//    private void createSizeCheckBox() {
//        var winBox = getUIFactoryService().newButton("RESOLUTION");
//        winBox.setLayoutX(50);
//        PetukhonApplication.setIntProperty("sizeX", 700);
//        PetukhonApplication.setIntProperty("sizeY", 700);
//        winBox.setOnAction(event -> {
//            var node = getUIFactoryService().newChoiceBox(
//                FXCollections.observableList(
//                    List.of(new Pair(600, 480), new Pair<>(700, 700), new Pair(1600, 900),
//                        new Pair(1920, 1080))));
//            node.setOnAction(
//                nodeEvent -> {
//                    PetukhonApplication.setIntProperty("sizeX",
//                        (Integer) node.getValue().getFirst());
//                    PetukhonApplication.setIntProperty("sizeY",
//                        (Integer) node.getValue().getSecond());
//                });
//            node.setLayoutX(50 * 6);
//            node.setLayoutY(getAppHeight() / 1.4);
//            getContentRoot().getChildren().add(node);
//        });
//        winBox.setLayoutY(getAppHeight() / 1.4);
//        getContentRoot().getChildren().add(winBox);
//    }

    private void createLengthCheckBox() {
        var winBox = getUIFactoryService().newButton("LENGTH FOR WIN");
        winBox.setLayoutX(50);
        PetukhonApplication.setIntProperty("ScoreToWin", 10);
        winBox.setOnAction(event -> {
            var node = getUIFactoryService().newChoiceBox(
                FXCollections.observableList(List.of(5, 10, 15, 20)));
            node.setOnAction(
                nodeEvent -> {
                    PetukhonApplication.setIntProperty("ScoreToWin", node.getValue());
                });
            node.setLayoutX(50 * 6);
            node.setLayoutY(getAppHeight() / 1.5);
            getContentRoot().getChildren().add(node);
        });
        winBox.setLayoutY(getAppHeight() / 1.5);
        getContentRoot().getChildren().add(winBox);
    }
}