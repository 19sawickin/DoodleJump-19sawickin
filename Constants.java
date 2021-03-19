package doodlejump;

/**
 * This is the Constants class. It defines some constants that the doodle and platforms
 * use as well as the dimensions of the game and other mathematical values that the kinematic
 * equations dictating the motion of the doodle use.
 */
public class Constants {

    public static final int GRAVITY = 1000; // acceleration constant (UNITS: pixels/s^2)
    public static final int REBOUND_VELOCITY = -900; // initial jump velocity (UNITS: pixels/s)
    public static final double DURATION = 0.016; // KeyFrame duration (UNITS: s)

    public static final int PLATFORM_WIDTH = 40; // (UNITS: pixels)
    public static final int PLATFORM_HEIGHT = 10; // (UNITS: pixels)
    public static final int DOODLE_WIDTH = 20; // (UNITS: pixels)
    public static final int DOODLE_HEIGHT = 40; // (UNITS: pixels)

    public static final int SCENE_WIDTH = 300;
    public static final int SCENE_HEIGHT = 600;
    public static final int MIDLINE = SCENE_HEIGHT/2;

    public static final int DOODLE_X_LOC = 100;
    public static final int DOODLE_Y_LOC = 100;
    public static final int PLATFORM_X = SCENE_WIDTH/2 + PLATFORM_WIDTH/2;
    public static final int PLATFORM_Y = SCENE_HEIGHT - PLATFORM_HEIGHT;

    public static final int DOODLE_X_DISTANCE = 20;

    public static final int X_OFFSET = 200;
    public static final int Y_OFFSET_MIN = PLATFORM_HEIGHT + 10;
    public static final int Y_OFFSET_MAX = 100;

}
