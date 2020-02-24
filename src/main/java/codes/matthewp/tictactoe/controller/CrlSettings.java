package codes.matthewp.tictactoe.controller;

import codes.matthewp.tictactoe.TicTacToe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class CrlSettings implements Initializable {

    @FXML
    private TextField heightText;

    @FXML
    private TextField widthText;

    @FXML
    private ComboBox<Label> langSelector;

    @FXML
    public void doClose(MouseEvent event) {
        Properties properties = new Properties();
        properties.setProperty("winHeight", heightText.getText());
        properties.setProperty("winWidth", widthText.getText());
        properties.setProperty("lang", langSelector.getValue().getText().toLowerCase());
        TicTacToe.config.save(properties);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        heightText.setText(TicTacToe.config.getString("winHeight"));
        widthText.setText(TicTacToe.config.getString("winWidth"));
        // TODO: Once more langs are added, add them here.
        langSelector.getItems().add(new Label("EN_US"));
        langSelector.setValue(new Label("EN_US"));
    }
}
