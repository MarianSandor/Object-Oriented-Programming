package brickbreaker.elements;

import java.awt.*;

public class Ball {

    private int x;
    private int y;
    private Direction direction;

    public Ball(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int[] getHitBox() {
        int corners[] = {this.x, this.x + 9, this.y, this.y + 9};
        return corners;
    }

    public int getLength() {
        return this.getHitBox()[1] - this.getHitBox()[0];
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        if (direction == Direction.DOWN) {
            this.y++;
        }
        if (direction == Direction.UP) {
            this.y--;
        }
        if (direction == Direction.RIGHT) {
            this.x++;
        }
        if (direction == Direction.LEFT) {
            this.x--;
        }
        if (direction == Direction.UP_LEFT) {
            this.x--;
            this.y--;
        }
        if (direction == Direction.UP_RIGHT) {
            this.x++;
            this.y--;
        }
        if (direction == Direction.DOWN_LEFT) {
            this.x--;
            this.y++;
        }
        if (direction == Direction.DOWN_RIGHT) {
            this.x++;
            this.y++;
        }
    }

    public boolean hitPlatform(Platform platform) {
        int hitBoxPlatform[] = platform.getHitBox();

        if (getHitBox()[0] >= hitBoxPlatform[0] - this.getLength() / 2 && getHitBox()[1] <= hitBoxPlatform[1] + this.getLength() / 2) {
            if (getHitBox()[3] >= hitBoxPlatform[2] && getHitBox()[3] < hitBoxPlatform[3]) {
                return true;
            }
        }
        return false;
    }

    public boolean hitBrick(Brick brick) {
        if ((this.getHitBox()[0] >= brick.getHitBox()[0] && this.getHitBox()[0] <= brick.getHitBox()[1]) || (this.getHitBox()[1] >= brick.getHitBox()[0] && this.getHitBox()[1] <= brick.getHitBox()[1])) {
            if ((this.getHitBox()[2] <= brick.getHitBox()[3] && this.getHitBox()[2] >= brick.getHitBox()[2]) || (this.getHitBox()[3] >= brick.getHitBox()[2] && this.getHitBox()[3] <= brick.getHitBox()[3])) {
                return true;
            }
        }

        return false;
    }

    public void drawBall(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(this.x, this.y, 10, 10);
    }

    @Override
    public String toString() {
        return "(" + this.x + " " + this.y + ")";
    }
}
