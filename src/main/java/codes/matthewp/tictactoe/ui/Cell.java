package codes.matthewp.tictactoe.ui;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Cell extends Pane {

    private CellDisplay display;

    public Cell() {
        super();
        this.display = CellDisplay.BLANK;
    }

    public void setDisplay(CellDisplay display) {
        this.display = display;
        if (getChildren().get(0) instanceof Text) {
            Text text = (Text) getChildren().get(0);
            text.setText(display.getText());
        }
    }

    public CellDisplay getDisplay() {
        return display;
    }
}
