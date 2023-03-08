package underwater.world.game.additional;

import underwater.world.game.constant.GameConstants;

public class Obstacle {

    private float x;
    private float y;
    private float speed;

    public Obstacle(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    // Move the obstacle based on its speed
    public void move() {
        x -= speed;
    }

    // Check if the obstacle has gone off the screen
    public boolean isOutOfScreen() {
        return x < -GameConstants.OBSTACLE_WIDTH;
    }

    // Check if the obstacle collides with the submarine
    public boolean collidesWith(Submarine submarine) {
        return x < submarine.getX() + GameConstants.SUBMARINE_WIDTH &&
                x + GameConstants.OBSTACLE_WIDTH > submarine.getX() &&
                y < submarine.getY() + GameConstants.SUBMARINE_HEIGHT &&
                y + GameConstants.OBSTACLE_HEIGHT > submarine.getY();
    }
}
