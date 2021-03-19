package doodlejump;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * The doodle class is responsible for constructing a doodle which is a rectangle
 * with dimensions and location specifications dictated in the constants class.
 * It also graphically adds the doodle to the doodle pane.
 */
public class Doodle {
    private Rectangle _doodle;

    /**
     * The doodle constructor calls the method that sets up the doodle and adds it
     * to the doodlePane graphically.
     */
    public Doodle(Pane doodlePane) {
        this.setupShape();
        doodlePane.getChildren().add(_doodle);
    }

    /**
     * This method constructs the shape of the doodle, sets its fill and starting
     * location.
     */
    public void setupShape() {
        _doodle = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
        _doodle.setFill(Color.BLUE);
        _doodle.setX(Constants.DOODLE_X_LOC); //SCENE_WIDTH/2
        _doodle.setY(Constants.DOODLE_Y_LOC);
    }

    /**
     * Getter method that returns the doodle's x-position
     */
    public double getX() {
        return _doodle.getX();
    }

    /**
     * Getter method that returns the doodle's y-position
     */
    public double getY() {
        return _doodle.getY();
    }

    /**
     * Setter method that sets the doodle's x-position
     */
    public void setX(double x_val) {
        _doodle.setX(x_val);
    }

    /**
     * Setter method that sets the doodle's y-position
     */
    public void setY(double y_val) {
        _doodle.setY(y_val);
    }

    /**
     * This method checks to see if the doodle intersects any of the platforms.
     */
    public boolean intersect_platform(double x, double y, double width, double height) {
        return _doodle.intersects(x, y, width, height);
    }
}
