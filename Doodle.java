package doodlejump;

import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class Doodle {
    private Rectangle _doodle;

    public Doodle(Pane doodlePane) {
        this.setupShape();
        doodlePane.getChildren().add(_doodle);
        doodlePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());

    }

    public void setupShape() {
        _doodle = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
        _doodle.setFill(Color.BLUE);
        _doodle.setX(Constants.DOODLE_X_LOC);
        _doodle.setY(Constants.DOODLE_Y_LOC);
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        private double _distance;

        public void handle(KeyEvent e) {
            _distance = Constants.DOODLE_DISTANCE;
            switch(e.getCode()) {
                case KP_LEFT:
                    _distance *= -1;
                    _doodle.setX(_doodle.getX() + _distance);
                    break;
                case KP_RIGHT:
                    _doodle.setX(_doodle.getX() + _distance);
                    break;
                default:
                    break;
            }

        }

    }
}
