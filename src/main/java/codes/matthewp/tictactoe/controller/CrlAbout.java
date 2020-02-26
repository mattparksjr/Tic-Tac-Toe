package codes.matthewp.tictactoe.controller;

import codes.matthewp.tictactoe.TicTacToe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CrlAbout implements Initializable {

    @FXML
    private Label aboutTitle;

    @FXML
    private Text aboutText;

    @FXML
    private Button aboutExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aboutTitle.setText(TicTacToe.lang.getString("aboutTitle"));
        aboutText.setText(TicTacToe.lang.getString("aboutText"));
        aboutExit.setText(TicTacToe.lang.getString("btnExit"));
    }

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
