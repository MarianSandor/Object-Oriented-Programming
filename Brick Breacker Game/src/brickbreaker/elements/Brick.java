package brickbreaker.elements;

import java.awt.*;

public class Brick {

    private int x;
    private int y;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getHitBox() {
        int corners[] = {this.x, this.x + 59, this.y, this.y + 29};
        return corners;
    }

    public void drawBrick(Graphics g) {
            g.setColor(new Color(8804653));
            g.fillRect(this.x, this.y, 60, 30);
    }

    @Override
    public String toString() {
        return "(" + this.x + " " + this.y + ")";
    }
}
