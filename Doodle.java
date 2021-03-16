package doodlejump;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class Doodle {
    private static Rectangle _doodle;

    public Doodle(Pane doodlePane) {
        this.setupShape();
        doodlePane.getChildren().add(_doodle);
    }

    public void setupShape() {
        _doodle = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
        _doodle.setFill(Color.BLUE);
        _doodle.setX(Constants.DOODLE_X_LOC);
        _doodle.setY(Constants.DOODLE_Y_LOC);
    }

    public static double getX() {
        return _doodle.getX();
    }
    public static void setX(double x_val) {
        _doodle.setX(x_val);
    }
}
