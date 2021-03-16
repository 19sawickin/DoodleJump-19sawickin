package doodlejump;

import cartoon.Cartoon;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class Game {
    private Doodle _doodle;
    private Platform _platform;

    public Game(BorderPane root, HBox _bottomPane, Pane doodlePane) {
        this.setupTimeline();
        _doodle = new Doodle(doodlePane);
        _platform = new Platform(root);
        new MoveHandler();
        root.setCenter(doodlePane);
        root.setBottom(_bottomPane);
        doodlePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
        doodlePane.setFocusTraversable(true);
        doodlePane.requestFocus();
    }

    public void setupTimeline() {
        Timeline timeline = new Timeline
                (new KeyFrame(Duration.seconds(0.1), new MoveHandler()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
