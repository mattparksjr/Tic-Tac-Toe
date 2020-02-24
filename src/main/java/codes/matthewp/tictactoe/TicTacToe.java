package codes.matthewp.tictactoe;

import codes.matthewp.tictactoe.file.IFile;
import codes.matthewp.tictactoe.multi.Side;
import codes.matthewp.tictactoe.protocol.PacketReader;
import codes.matthewp.tictactoe.util.GuiUtil;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    public static final Image ICON_IMAGE = new Image("/icon.png");

    public static IFile lang;
    public static IFile config;
    public static final String COMM_PRO_VER = "0.0.1";
    public static Side side;

    @Override
    public void start(Stage primaryStage) {
        GuiUtil.openNewWindow(primaryStage, "main_menu");
        new PacketReader();
    }

    public static void main(String[] args) {
        System.out.println("Starting Tic-Tac-Toe!");
        lang = new IFile("en_us", true);
        config = new IFile("settings", false);
        System.out.println("Files loaded. Loading networking parts...");

        System.out.println("Networking done. Launching UI");
        launch(args);
    }
}
