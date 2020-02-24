package codes.matthewp.tictactoe.controller;

import codes.matthewp.tictactoe.TicTacToe;
import codes.matthewp.tictactoe.multi.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

public class CrlHostGame implements Initializable {

    @FXML
    private TextField roomName;

    @FXML
    private TextField roomPassword;

    @FXML
    private CheckBox pwOnOff;

    @FXML
    public Label statusText;

    @FXML
    private void mClick(ActionEvent e) {
        if (e.getSource() instanceof CheckBox) {
            CheckBox chk = (CheckBox) e.getSource();
            roomPassword.setDisable(! chk.isSelected());
        }
    }

    @FXML
    public void startHost(MouseEvent event) {
        statusText.setText(TicTacToe.lang.getString("creatingServer"));
        new Thread(() -> {
            try (ServerSocket listener = new ServerSocket(59599)) {
                try {
                    new Server(this, listener.accept());
                } catch (IOException e) {
                    e.printStackTrace();
                    statusText.setText(TicTacToe.lang.getString("errSrvWaitThread"));
                }
            } catch (IOException ex) {
                System.out.println("error creating server");
                ex.printStackTrace();
                statusText.setText(String.format(TicTacToe.lang.getString("errSrvThreadInit")));
            }
        }).start();
        try {
            InetAddress local = InetAddress.getLocalHost();
            statusText.setText(String.format(TicTacToe.lang.getString("waitingOppIP"), local.getHostAddress()));
        } catch (UnknownHostException ex) {
            // what are the running this on? a toaster???
            ex.printStackTrace();
            statusText.setText(TicTacToe.lang.getString("errRunningToaster"));
        }
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
        statusText.setText("");
        roomPassword.setDisable(true);
    }
}
