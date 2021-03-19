package doodlejump;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * The platform class is responsible for constructing platforms on the screen.
 */
public class Platform {
    private Rectangle _platform;

    /**
     * Each time the platform constructor is called, a new rectangle is constructed
     * and the color is set. It is also graphically added to the doodle pane.
     */
    public Platform(Pane doodlePane) {
        _platform = new Rectangle(Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        _platform.setFill(Color.GREEN);
        doodlePane.getChildren().add(_platform);
    }

    /**
     * This getter method returns a platform
     */
    public Rectangle getPlatform() {
        return _platform;
    }

    /**
     * This getter method returns the x-location of a platform.
     */
    public int getX() {
        return (int) _platform.getX();
    }

    /**
     * This getter method returns the y-location of a platform.
     */
    public int getY() {
        return (int) _platform.getY();
    }

    /**
     * This setter method sets the x-location of a platform.
     */
    public void setX(int x) {
        _platform.setX(x);
    }

    /**
     * This setter method sets the y-location of a platform.
     */
    public void setY(int y) {
        _platform.setY(y);
    }
}
