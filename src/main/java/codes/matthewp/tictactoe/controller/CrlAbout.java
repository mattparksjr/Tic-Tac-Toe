package codes.matthewp.tictactoe.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CrlAbout {

    @FXML
    public void doClose(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void mouseEnter(MouseEvent event) {
        Button button = (Button) event.getTarget();
        button.getStyleClass().add("menu-button-hover");
    }

    @FXML
    public void mouseExit(MouseEvent event) {
        Button button = (Button) event.getTarget();
        button.getStyleClass().remove("menu-button-hover");
    }
}
