package brickbreaker.elements;

import java.awt.*;

public class Platform {

    private int x;
    private int y;

    public Platform(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int[] getHitBox() {
        int corners[] = {this.x, this.x + 99, this.y, this.y + 9};
        return corners;
    }

    public void move(Direction direction) {
        if (direction == Direction.LEFT) {
            this.x -= 5;
        }
        if (direction == Direction.RIGHT) {
            this.x += 5;
        }
    }

    public void drawPlatform(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(this.x, this.y, 100, 10,10,10);
    }
}
