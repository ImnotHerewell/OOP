open module ru.nsu.valikov.petukhon {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
//    requires annotations;
//    opens ru.nsu.valikov.petukhon to javafx.fxml;
    exports ru.nsu.valikov.petukhon;
    exports ru.nsu.valikov.petukhon.factories;
}