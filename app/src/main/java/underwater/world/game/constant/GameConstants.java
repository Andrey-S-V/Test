package underwater.world.game.constant;

public class GameConstants {

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;

    public static final float SUBMARINE_WIDTH = 250;
    public static final float SUBMARINE_HEIGHT = 100;
    public static final float SUBMARINE_SPEED = 12;
    public static final float SUBMARINE_MAX_Y = SCREEN_HEIGHT - SUBMARINE_HEIGHT;
    public static final float SUBMARINE_MIN_Y = 0;

    public static final float OBSTACLE_WIDTH = 200;
    public static final float OBSTACLE_HEIGHT = 100;
    public static final float OBSTACLE_SPEED = 18;
    public static final float OBSTACLE_MAX_Y = SCREEN_HEIGHT - OBSTACLE_HEIGHT;
    public static final float OBSTACLE_MIN_Y = 0;

    public static final int MAX_OBSTACLES = 3;
    public static final int SCORE_INCREMENT = 10;
}

