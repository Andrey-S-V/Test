package underwater.world.game.additional;

public class Submarine {
    private int x;
    private int y;
    private int direction;
    private int speed;

    public Submarine(int startX, int startY) {
        x = startX;
        y = startY;
        direction = 0;
        speed = 1;
    }

    public void move() {
        // Update x and y coordinates based on current direction and speed
        switch(direction) {
            case 0: // North
                y -= speed;
                break;
            case 1: // East
                x += speed;
                break;
            case 2: // South
                y += speed;
                break;
            case 3: // West
                x -= speed;
                break;
        }
    }

    public void turnLeft() {
        direction = (direction + 3) % 4; // Turning left is equivalent to turning right three times
    }

    public void turnRight() {
        direction = (direction + 1) % 4;
    }

    public void increaseSpeed() {
        speed++;
    }

    public void decreaseSpeed() {
        if (speed > 1) { // Speed can't be negative or zero
            speed--;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }
}

