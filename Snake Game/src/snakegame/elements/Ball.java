package snakegame.elements;

import java.awt.*;

public class Ball {

    private int raze;
    private int x;
    private int y;

    public Ball(int dx, int dy, int raze) {
        this.x = dx;
        this.y = dy;
        this.raze = raze;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRaze() {  return this.raze; }

    public void drawFigure(Graphics graphics) {
        graphics.fillOval(this.x, this.y, this.raze, this.raze);
    }

    @Override
    public String toString() {
        return "X = " + this.x + "; Y = " + this.y;
    }
}
