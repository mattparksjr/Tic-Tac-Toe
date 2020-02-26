package codes.matthewp.tictactoe.controller;

import codes.matthewp.tictactoe.util.GuiUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CrlMultiPlayer {

    @FXML
    public void doHost(MouseEvent event) {
        GuiUtil.openNewWindow(((Node) (event.getSource())).getScene().getWindow(), "host_game", 286, 292, false);
    }

    @FXML
    public void doJoin(MouseEvent event) {
        GuiUtil.openNewWindow(((Node) (event.getSource())).getScene().getWindow(),
                "join_game");
    }

    @FXML
    public void doTitleScreen(MouseEvent event) {
        GuiUtil.openNewWindow(((Node) (event.getSource())).getScene().getWindow(), "main_menu");
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
