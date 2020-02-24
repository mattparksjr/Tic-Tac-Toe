package codes.matthewp.tictactoe.controller;

import codes.matthewp.tictactoe.util.GuiUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CrlMainMenu {

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

    @FXML
    public void doSingle(MouseEvent event) {
        GuiUtil.openNewWindow(((Node) (event.getSource())).getScene().getWindow(),
                "single_player");
    }

    @FXML
    public void doMulti(MouseEvent event) {
        GuiUtil.openNewWindow(((Node) (event.getSource())).getScene().getWindow(),
                "multi_player");
    }

    @FXML
    public void doSettings(MouseEvent event) {
        GuiUtil.openNewWindow(((Node) (event.getSource())).getScene().getWindow(),
                "settings", 292.0, 344, false);
    }

    @FXML
    private void doAbout(MouseEvent event) {
        GuiUtil.openNewWindow(((Node) (event.getSource())).getScene().getWindow(),
                "about", 292.0, 344, false);
    }

    @FXML
    public void doExit(MouseEvent event) {
        Button button = (Button) event.getTarget();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

}
