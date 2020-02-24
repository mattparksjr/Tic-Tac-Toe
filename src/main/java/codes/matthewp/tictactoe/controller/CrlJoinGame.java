package codes.matthewp.tictactoe.controller;

import codes.matthewp.tictactoe.multi.client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class CrlJoinGame implements Initializable {

    @FXML
    private ListView<Label> gameList;

    @FXML
    private TextField serverText;

    @FXML
    public Label infoText;

    @FXML
    public void onHover(MouseEvent event) {
        Button button = (Button) event.getTarget();
        button.getStyleClass().add("menu-button-hover");
    }

    @FXML
    public void onHoverExit(MouseEvent event) {
        Button button = (Button) event.getTarget();
        button.getStyleClass().remove("menu-button-hover");
    }

    @FXML
    public void doDIrConnect(MouseEvent event) {
        new Thread(() -> {
            try {
                new Client(this, new Socket(serverText.getText(), 59599));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Label> list = FXCollections.observableArrayList(
                new Label("Loading servers..."));
        gameList.setItems(list);
    }
}
