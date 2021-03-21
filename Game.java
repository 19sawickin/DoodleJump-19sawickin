package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * The Game class contains most of the logic of doodlejump. It sets up a timeline
 * and keyhandler which are responsible for updating the position of the doodle and
 * the platforms both graphically and logically when keys are pressed and when the
 * doodle is in various parts of the screen.
 */
public class Game {
    private Doodle _doodle;
    private Pane _doodlePane;
    private Timeline _timeline;
    private BorderPane _root;
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
    private double _distance;

    /**
     * The Game constructor is responsible for adding the proper elements their
     * respective panes graphically as well as for constructing the array list
     * that contains all of the platforms. It also generates the first platform
     * and stores it in the array list.
     */
    public Game(BorderPane root, HBox _bottomPane, Pane doodlePane) {
//        this.setupTimeline();
//        _doodle = new Doodle(doodlePane);
//        _doodlePane = doodlePane;
//        _root = root;
//        root.setCenter(_doodlePane);
//        root.setBottom(_bottomPane);
//        doodlePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
//        doodlePane.setFocusTraversable(true);
//        doodlePane.requestFocus();
//        // code below sets up first platform and array list
//        _platformList = new ArrayList<Platform>();
//        _firstPlatform = new Platform(_doodlePane);
//        _firstPlatform.setX(Constants.PLATFORM_X);
//        _firstPlatform.setY(Constants.PLATFORM_Y);
//        _topPlatform = _firstPlatform;
//        _platformList.add(_topPlatform);
    }

    /**
     * This class is responsible for creating the timeline and setting how
     * often it updates.
     */
    public void setupTimeline() {
        _timeline = new Timeline
                (new KeyFrame(Duration.seconds(Constants.DURATION), new TimeHandler()));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    /**
     * The keyhandler class is what allows the user to press the right and left keys
     * and have the doodle respond graphically and logically to these actions.
     * A switch is used to handle these different cases.
     */
    private class KeyHandler implements EventHandler<KeyEvent> {

        public void handle(KeyEvent e) {
            _distance = Constants.DOODLE_X_DISTANCE;
            double x_val;

            switch(e.getCode()) {
                case LEFT:
                    _distance *= -1;
                    if(_doodle.getX() > 0){
                        x_val = _doodle.getX() + _distance;
                    } else {
                        x_val = Constants.SCENE_WIDTH + _distance;
                    }
                    _doodle.setX(x_val);
                    break;
                case RIGHT:
                    if(_doodle.getX() < Constants.SCENE_WIDTH) {
                        x_val = _doodle.getX() + _distance;
                    } else {
                        x_val = _distance;
                    }
                    _doodle.setX(x_val);
                    break;
                default:
                    break;
            }
            e.consume();
        }
    }

    /**
     * The timehandler class contains a lot of the game logic in terms of how the doodle
     * and platforms interact and move.
     */
    private class TimeHandler implements EventHandler<ActionEvent> {

        /**
         * The constructor initializes all of the position variables and finds
         * the midline value.
         */
        public TimeHandler() {
            _cVel = 0;
            _newVel = 0;
            _newPos = 0;
            _difference = 0;
            _midline = Constants.MIDLINE;
            _topPlatform = _firstPlatform;
        }

        /**
         * The handler is called every 0.016 seconds and checks to see where the doodle's
         * position is. If it's on the screen, the doodle will go through a series of checks
         * to see if it is interacting with a platform and move according.
         */
        public void handle(ActionEvent kF) {
            if(_doodle.getY() > Constants.SCENE_HEIGHT) {
                _timeline.stop();
                _doodlePane.getChildren().add(new Label("GAME OVER"));
                _doodlePane.setOnKeyPressed(null);
            }
            _cPos = _doodle.getY();
            _newVel = _cVel + Constants.GRAVITY * Constants.DURATION;
            _newPos = _cPos + _newVel * Constants.DURATION;
            _doodle.setY(_newPos);
            this.movePlatforms();
            this.removePlatforms();
            this.generatePlatforms();
            _cVel = _newVel;
            this.bounce();
        }

        /**
         * The bounce method is responsible for changing the velocity of the doodle if
         * it interacts with a platform. It essentially reverses it from falling to
         * jumping.
         */
        public void bounce() {
            for(Platform _platform: _platformList) {
                if(_cVel >=0 && _doodle.intersect_platform(_platform.getX(), _platform.getY(),
                        Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT )) {
                    _cVel = Constants.REBOUND_VELOCITY;
                }
            }
        }

        /**
         * This method is responsible for graphically and logically removing platforms
         * as the doodle "moves higher" on the screen.
         */
        public void removePlatforms() {
            for(int i=0; i<_platformList.size(); i++) {
                if (_platformList.get(i).getY() > Constants.SCENE_HEIGHT) {
                    _doodlePane.getChildren().remove(_platformList.get(i).getPlatform());
                    _platformList.remove(_platformList.get(i));
                    i--;
                }
            }
        }

        /**
         * This method is in charge of scrolling all of the platforms. If the doodle is
         * on the bottom half of the screen, the doodle's distance is adjusted using
         * kinematic equations. If it's on the top half of the screen, the platforms all
         * move up and the doodle is brought back down to the midline to give the impression
         * of the doodle moving up.
         */
        public void movePlatforms() {
            if(_doodle.getY() < _midline) {
                _difference = _midline - _doodle.getY();
                for(Platform _platform: _platformList) {
                    _platform.setY(_platform.getY() + (int) _difference);
                }
                _doodle.setY(_midline);
            }
        }

        /**
         * This method is responsible for semi-randomly generating platforms and
         * adding them logically to the platform's array list.
         */
        public void generatePlatforms() {
            while(_topPlatform.getY() > 0) {
                _low = Math.max(0,_topPlatform.getX() - Constants.X_OFFSET);
                _high = Math.min(Constants.SCENE_WIDTH - Constants.PLATFORM_WIDTH,
                        _topPlatform.getX() + Constants.X_OFFSET);
                _randomX = _low + (int) ((_high-_low+1) * Math.random());
                _low = _topPlatform.getY() - Constants.Y_OFFSET_MIN;
                _high = _topPlatform.getY() - Constants.Y_OFFSET_MAX;
                _randomY = _low + (int) ((_high-_low+1) * Math.random());
                _newPlatform = new Platform(_doodlePane);
                _newPlatform.setX((int) _randomX);
                _newPlatform.setY((int) _randomY);
                _platformList.add(_newPlatform);
                _topPlatform = _newPlatform;
            }
        }
    }
}
