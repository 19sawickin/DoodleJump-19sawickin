package doodlejump;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Platform {
    private static Rectangle _platform;

    public Platform(BorderPane root) {
        this.setupShape();
        root.getChildren().add(_platform);

    }

    public void setupShape() {
        _platform = new Rectangle(Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        _platform.setFill(Color.GREEN);
        _platform.setX(Constants.SCENE_WIDTH/2);
        _platform.setY(Constants.SCENE_HEIGHT-Constants.PLATFORM_HEIGHT);
    }
}
