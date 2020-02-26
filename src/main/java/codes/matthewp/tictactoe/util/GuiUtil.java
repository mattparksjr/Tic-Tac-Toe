package codes.matthewp.tictactoe.util;

import codes.matthewp.tictactoe.TicTacToe;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;

public class GuiUtil {

    public static void openNewWindow(Stage stage, String resourcePath) {
        Parent root;
        try {
            URL url = GuiUtil.class.getResource("/ui/" + resourcePath + ".fxml");
            if (url != null) {
                root = FXMLLoader.load(url);
                stage.setTitle(TicTacToe.lang.getString("title"));
                stage.setScene(new Scene(root,
                        TicTacToe.config.getDouble("winWidth"),
                        TicTacToe.config.getDouble("winHeight")));
                stage.getIcons().add(TicTacToe.ICON_IMAGE);
                stage.show();
            } else {
                System.out.println("URL WAS NULL :(");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void openNewWindow(Window window, String resourcePath) {
        Parent root;
        try {
            URL url = GuiUtil.class.getResource("/ui/" + resourcePath + ".fxml");
            if (url != null) {
                root = FXMLLoader.load(url);
                Stage stage = new Stage();
                stage.setTitle(TicTacToe.lang.getString("title"));
                stage.setScene(new Scene(root,
                        TicTacToe.config.getDouble("winWidth"),
                        TicTacToe.config.getDouble("winHeight")));
                stage.getIcons().add(TicTacToe.ICON_IMAGE);
                stage.show();
                stage.setX(window.getX());
                stage.setY(window.getY());
                window.hide();
            } else {
                System.out.println("URL WAS NULL :(");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void openNewWindow(Window window, String resourcePath, double width, double height, boolean closeOld) {
        Parent root;
        try {
            URL url = GuiUtil.class.getResource("/ui/" + resourcePath + ".fxml");
            if (url != null) {
                root = FXMLLoader.load(url);
                Stage stage = new Stage();
                stage.setTitle(TicTacToe.lang.getString("title"));
                stage.setScene(new Scene(root, width, height));
                stage.getIcons().add(TicTacToe.ICON_IMAGE);
                stage.show();
                stage.setX(window.getX());
                stage.setY(window.getY());
                if (closeOld) {
                    window.hide();
                    stage.setX(window.getX());
                    stage.setY(window.getY());
                } else {
                    stage.setX(window.getX() + ((window.getWidth() - stage.getWidth()) / 2));
                    stage.setY(window.getY() + ((window.getHeight() - stage.getHeight()) / 2));
                }
            } else {
                System.out.println("URL WAS NULL :(");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
