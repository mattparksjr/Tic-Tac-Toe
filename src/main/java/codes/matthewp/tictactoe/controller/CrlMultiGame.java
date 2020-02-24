package codes.matthewp.tictactoe.controller;

import codes.matthewp.tictactoe.TicTacToe;
import codes.matthewp.tictactoe.ui.Cell;
import codes.matthewp.tictactoe.ui.CellDisplay;
import codes.matthewp.tictactoe.util.GuiUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CrlMultiGame implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private Text winText;

    @FXML
    private Button resetBtn;

    @FXML
    private Label xWinsText;

    @FXML
    private Label oWinsText;

    @FXML
    private Label drawCountText;

    private CellDisplay lastDisplay;

    private int moveCount = 0;
    private int xWins = 0;
    private int oWins = 0;
    private int dCount = 0;

    @FXML
    private void doReset(MouseEvent e) {
        lastDisplay = null;
        moveCount = 0;
        winText.setText("");
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Cell) {
                ((Cell) node).setDisplay(CellDisplay.BLANK);
            }
        }
        winText.setLayoutX(209);
        winText.setLayoutY(381);
        resetBtn.setVisible(false);
    }

    @FXML
    private void doTitleScreen(MouseEvent e) {
        TicTacToe.side.disconnect();
        GuiUtil.openNewWindow(((Node) (e.getSource())).getScene().getWindow(), "main_menu");
    }

    @FXML
    private void mouseClick(MouseEvent e) {
        if (e.getTarget() instanceof Cell) {
            Cell cell = (Cell) e.getTarget();
            if (! canSetDisplay(cell)) return;
            if (lastDisplay == null) {
                doMove(cell, CellDisplay.X);
            } else if (lastDisplay == CellDisplay.X) {
                doMove(cell, CellDisplay.O);
            } else if (lastDisplay == CellDisplay.O) {
                doMove(cell, CellDisplay.X);
            }
        } else if (e.getTarget() instanceof Text) {
            Text text = (Text) e.getTarget();
            if (text.getParent() instanceof Cell) {
                Cell cell = (Cell) text.getParent();
                if (! canSetDisplay(cell)) return;
                if (lastDisplay == null) {
                    doMove(cell, CellDisplay.X);
                } else if (lastDisplay == CellDisplay.X) {
                    doMove(cell, CellDisplay.O);
                } else if (lastDisplay == CellDisplay.O) {
                    doMove(cell, CellDisplay.X);
                }
            }
        }
    }

    @FXML
    private void doHover(MouseEvent event) {
        Cell cell = (Cell) event.getTarget();
        cell.getStyleClass().add("hover");
    }

    @FXML
    private void removeHover(MouseEvent event) {
        Cell cell = (Cell) event.getTarget();
        cell.getStyleClass().remove("hover");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Cell) {
                Cell cell = (Cell) node;
                cell.setDisplay(CellDisplay.BLANK);
            }
        }
        winText.setText("");
        resetBtn.setVisible(false);
        drawWins();
    }

    private boolean canSetDisplay(Cell cell) {
        return cell.getDisplay() == CellDisplay.BLANK;
    }

    private void doMove(Cell cell, CellDisplay display) {
        moveCount++;

        if (! canSetDisplay(cell)) return;
        lastDisplay = display;
        cell.setDisplay(lastDisplay);

        CellDisplay winner = null;

        // col check
        if (((Cell) gridPane.getChildren().get(0)).getDisplay() ==
                ((Cell) gridPane.getChildren().get(3)).getDisplay()
                && ((Cell) gridPane.getChildren().get(3)).getDisplay() != CellDisplay.BLANK) {
            if (((Cell) gridPane.getChildren().get(3)).getDisplay() ==
                    ((Cell) gridPane.getChildren().get(6)).getDisplay()) {
                winner = ((Cell) gridPane.getChildren().get(0)).getDisplay();
            }
        }
        if (((Cell) gridPane.getChildren().get(1)).getDisplay() ==
                ((Cell) gridPane.getChildren().get(4)).getDisplay()
                && ((Cell) gridPane.getChildren().get(4)).getDisplay() != CellDisplay.BLANK) {
            if (((Cell) gridPane.getChildren().get(4)).getDisplay() ==
                    ((Cell) gridPane.getChildren().get(7)).getDisplay()) {
                winner = ((Cell) gridPane.getChildren().get(1)).getDisplay();
            }
        }
        if (((Cell) gridPane.getChildren().get(2)).getDisplay() ==
                ((Cell) gridPane.getChildren().get(5)).getDisplay()
                && ((Cell) gridPane.getChildren().get(5)).getDisplay() != CellDisplay.BLANK) {
            if (((Cell) gridPane.getChildren().get(5)).getDisplay() ==
                    ((Cell) gridPane.getChildren().get(8)).getDisplay()) {
                winner = ((Cell) gridPane.getChildren().get(2)).getDisplay();
            }
        }

        // row check
        if (((Cell) gridPane.getChildren().get(0)).getDisplay() ==
                ((Cell) gridPane.getChildren().get(1)).getDisplay()
                && ((Cell) gridPane.getChildren().get(1)).getDisplay() != CellDisplay.BLANK) {
            if (((Cell) gridPane.getChildren().get(1)).getDisplay() ==
                    ((Cell) gridPane.getChildren().get(2)).getDisplay()) {
                winner = ((Cell) gridPane.getChildren().get(0)).getDisplay();
            }
        }
        if (((Cell) gridPane.getChildren().get(3)).getDisplay() ==
                ((Cell) gridPane.getChildren().get(4)).getDisplay()
                && ((Cell) gridPane.getChildren().get(4)).getDisplay() != CellDisplay.BLANK) {
            if (((Cell) gridPane.getChildren().get(4)).getDisplay() ==
                    ((Cell) gridPane.getChildren().get(5)).getDisplay()) {
                winner = ((Cell) gridPane.getChildren().get(3)).getDisplay();
            }
        }
        if (((Cell) gridPane.getChildren().get(6)).getDisplay() ==
                ((Cell) gridPane.getChildren().get(7)).getDisplay()
                && ((Cell) gridPane.getChildren().get(7)).getDisplay() != CellDisplay.BLANK) {
            if (((Cell) gridPane.getChildren().get(7)).getDisplay() ==
                    ((Cell) gridPane.getChildren().get(8)).getDisplay()) {
                winner = ((Cell) gridPane.getChildren().get(6)).getDisplay();
            }
        }

        // diag check
        if (((Cell) gridPane.getChildren().get(0)).getDisplay() ==
                ((Cell) gridPane.getChildren().get(4)).getDisplay()
                && ((Cell) gridPane.getChildren().get(4)).getDisplay() != CellDisplay.BLANK) {
            if (((Cell) gridPane.getChildren().get(4)).getDisplay() ==
                    ((Cell) gridPane.getChildren().get(8)).getDisplay()) {
                winner = ((Cell) gridPane.getChildren().get(0)).getDisplay();
            }
        }

        if (((Cell) gridPane.getChildren().get(2)).getDisplay() ==
                ((Cell) gridPane.getChildren().get(4)).getDisplay()
                && ((Cell) gridPane.getChildren().get(4)).getDisplay() != CellDisplay.BLANK) {
            if (((Cell) gridPane.getChildren().get(4)).getDisplay() ==
                    ((Cell) gridPane.getChildren().get(6)).getDisplay()) {
                winner = ((Cell) gridPane.getChildren().get(2)).getDisplay();
            }
        }

        if (moveCount == 9 && winner == null) {
            winText.setText(TicTacToe.lang.getString("draw"));
            winText.setLayoutX(273);
            winText.setLayoutY(380);
            resetBtn.setVisible(true);
            dCount = dCount + 1;
            drawWins();
        } else if (winner != null) {
            if (winner == CellDisplay.X) {
                winText.setText(TicTacToe.lang.getString("xWin"));
                xWins = xWins + 1;
            } else {
                winText.setText(TicTacToe.lang.getString("oWin"));
                oWins = oWins + 1;
            }
            resetBtn.setVisible(true);
            drawWins();
        }
    }

    private void drawWins() {
        xWinsText.setText(
                String.format(TicTacToe.lang.getString("xWinCounter"), xWins));
        oWinsText.setText(
                String.format(TicTacToe.lang.getString("oWinCounter"), oWins));
        drawCountText.setText(
                String.format(TicTacToe.lang.getString("drawCounter"), dCount));
    }
}
