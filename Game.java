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
    private int _midline;
    private double _c_vel;
    private double _new_vel;
    private double _c_pos;
    private double _new_pos;
    private double _difference;

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
            double x_val;

            switch(e.getCode()) {
                case LEFT:
                    _distance *= -1;
                    x_val = Doodle.getX() + _distance;
                    Doodle.setX(x_val);
                    break;
                case RIGHT:
                    x_val = Doodle.getX() + _distance;
                    Doodle.setX(x_val);
                    break;
                default:
                    break;
            }
            e.consume();
        }
    }

    private class MoveHandler implements EventHandler<ActionEvent> {

        public MoveHandler() {
            _c_vel = 0;
            _new_vel = 0;
            _new_pos = 0;
            _difference = 0;
            _midline = Constants.MIDLINE;
        }

        public void handle(ActionEvent kF) {
            _c_pos = Doodle.getY();
            _new_vel = _c_vel + Constants.GRAVITY * Constants.DURATION;
            _new_pos = _c_pos + _new_vel * Constants.DURATION;

            if(_new_pos > _midline) {
                Doodle.setY(_c_pos + _new_vel * Constants.DURATION);
            } else {
                _difference = _midline - _new_pos;
                //move platforms down by difference
                //remove off-screen platforms
                //generate new platforms
                Doodle.setY(_midline);
            }
            _c_vel = _new_vel;
            this.bounce();
        }

        public void bounce() {
            if(_c_pos>=0 && Doodle.intersect_platform(Constants.PLATFORM_X, Constants.PLATFORM_Y,
                    Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT )) {
                _c_vel = Constants.REBOUND_VELOCITY;
            }
        }
    }
}
