package doodlejump;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Platform {
    private Rectangle _platform;
    private Rectangle _firstPlatform;

    public Platform(Pane doodlePane) {
        _platform = new Rectangle(Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        _platform.setFill(Color.GREEN);
        doodlePane.getChildren().add(_platform);
    }

    public Rectangle getPlatform() {
        return _platform;
    }

    public int getX() {
        return (int) _platform.getX();
    }

    public int getY() {
        return (int) _platform.getY();
    }

    public void setX(int x) {
        _platform.setX(x);
    }

    public void setY(int y) {
        _platform.setY(y);
    }
}
