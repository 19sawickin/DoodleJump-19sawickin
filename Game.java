package doodlejump;

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
                (new KeyFrame(Duration.seconds(Constants.DURATION), new MoveHandler()));
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
        private double _c_vel = 0;
        private double _new_vel = 0;
        private double _c_pos;
        private double _new_pos = 0;
        private double _difference = 0;

        public void handle(ActionEvent kF) {
            _c_pos = Doodle.getY();
            _new_vel = _c_vel + Constants.GRAVITY * Constants.DURATION;
            _new_pos = _c_pos + _new_vel * Constants.DURATION;

            if(_new_pos > Constants.SCENE_HEIGHT/2) {
                Doodle.setY(_c_pos + _new_vel * Constants.DURATION);
                _c_vel = _new_vel; //this screws things up
            } else {
                _difference = Constants.SCENE_HEIGHT/2 - Doodle.getY();
                //move platforms down by difference
                //remove off-screen platforms
                //generate new platforms
                Doodle.setY(Constants.SCENE_HEIGHT/2);
            }

            if(_c_pos>=0 && _doodle.intersects(Constants.PLATFORM_X, Constants.PLATFORM_Y,
                    Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT )) {
                _c_vel = Constants.REBOUND_VELOCITY;
            }
        }
    }
}
