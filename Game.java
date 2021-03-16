package doodlejump;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class Game {
    private Doodle _doodle;
    private Platform _platform;

    public Game(BorderPane root, HBox _bottomPane, Pane doodlePane) {
        _doodle = new Doodle(doodlePane);
        new MoveHandler();
        root.setCenter(doodlePane);
        root.setBottom(_bottomPane);
        doodlePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
        doodlePane.setFocusTraversable(true);
        doodlePane.requestFocus();
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        private double _distance;

        public void handle(KeyEvent e) {
            _distance = Constants.DOODLE_DISTANCE;
            double x_val = Doodle.getX() + _distance;
            switch(e.getCode()) {
                case KP_LEFT:
                    _distance *= -1;
                    Doodle.setX(x_val);
                    break;
                case KP_RIGHT:
                    Doodle.setX(x_val);
                    break;
                default:
                    break;
            }
            e.consume();
        }

    }

    private class MoveHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {

        }
    }
}
