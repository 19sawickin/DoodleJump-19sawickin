package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game {
    private Doodle _doodle;

    private Platform _firstPlatform;
    private ArrayList<Platform> _platformList;
    private Platform _topPlatform;
    private Platform _newPlatform;
    private double _low;
    private double _high;
    private double _randomX;
    private double _randomY;

    private int _midline;
    private double _cVel;
    private double _newVel;
    private double _cPos;
    private double _newPos;
    private double _difference;

    public Game(BorderPane root, HBox _bottomPane, Pane doodlePane) {
        this.setupTimeline();
        _doodle = new Doodle(doodlePane);

        new TimeHandler(); //wait do I need this???
        root.setCenter(doodlePane);
        root.setBottom(_bottomPane);
        doodlePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
        doodlePane.setFocusTraversable(true);
        doodlePane.requestFocus();

        _platformList = new ArrayList<Platform>();
        _firstPlatform = new Platform();
        _firstPlatform.setX(Constants.PLATFORM_X); // IDK WHAT TO DO HERE
        _firstPlatform.setY(Constants.PLATFORM_Y);
        _topPlatform = _firstPlatform;
        _platformList.add(_topPlatform);
        root.getChildren().add(_firstPlatform);
    }

    public void setupTimeline() {
        Timeline timeline = new Timeline
                (new KeyFrame(Duration.seconds(Constants.DURATION), new TimeHandler()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        private double _distance;

        public void handle(KeyEvent e) {
            _distance = Constants.DOODLE_X_DISTANCE;
            double x_val;

            switch(e.getCode()) {
                case LEFT:
                    _distance *= -1;
                    x_val = _doodle.getX() + _distance; //
                    _doodle.setX(x_val); //
                    break;
                case RIGHT:
                    x_val = _doodle.getX() + _distance; //
                    _doodle.setX(x_val); //
                    break;
                default:
                    break;
            }
            e.consume();
        }
    }

    private class TimeHandler implements EventHandler<ActionEvent> {

        public TimeHandler() {
            _cVel = 0;
            _newVel = 0;
            _newPos = 0;
            _difference = 0;
            _midline = Constants.MIDLINE;
            _topPlatform = _firstPlatform;
        }

        public void handle(ActionEvent kF) {
            _cPos = _doodle.getY(); //
            _newVel = _cVel + Constants.GRAVITY * Constants.DURATION;
            _newPos = _cPos + _newVel * Constants.DURATION;

            if(_newPos > _midline) {
                _doodle.setY(_cPos + _newVel * Constants.DURATION); //
            } else {
                _difference = _midline - _newPos;
                //move platforms down by difference
                //remove off-screen platforms
                //generate new platforms
                _doodle.setY(_midline); //
            }
            _cVel = _newVel;
            this.bounce();
        }

        public void bounce() {
            if(_cPos >=0 && _doodle.intersect_platform(Constants.PLATFORM_X, Constants.PLATFORM_Y,
                    Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT )) {
                _cVel = Constants.REBOUND_VELOCITY;
            }
        }

        public void generatePlatforms() {
            while(_topPlatform.getY() > 0) {
                _low = Math.max(0,_topPlatform.getX() - Constants.DOODLE_X_DISTANCE);
                _high = Math.min(Constants.SCENE_WIDTH - Constants.PLATFORM_WIDTH,
                        _topPlatform.getX() + Constants.DOODLE_X_DISTANCE);
                _randomX = Math.random(_low,_high)*10;

                _low = _topPlatform.getY() - Constants.PLATFORM_HEIGHT;
                _high = _topPlatform.getY() - Constants.DOODLE_Y_DISTANCE;
                _randomY = Math.random(_low,_high)*10;

                _newPlatform = new Platform();
                _newPlatform.setX(_randomX);
                _newPlatform.setY(_randomY);

                _topPlatform = _newPlatform;
                root.getChildren().add(_topPlatform);

            }


        }

    }
}
