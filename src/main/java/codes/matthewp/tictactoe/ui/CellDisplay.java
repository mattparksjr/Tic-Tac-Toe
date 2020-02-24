package codes.matthewp.tictactoe.ui;

public enum CellDisplay {
    X("X"), O("O"), BLANK("");

    private String text;

    CellDisplay(String txt) {
        text = txt;
    }

    public String getText() {
        return text;
    }
}
