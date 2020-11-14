package snakegame.elements;

import java.awt.*;

public class Piece {

    private int x;
    private int y;
    private int sideLength;

    public Piece(int x, int y, int sideLength) {
        this.x = x;
        this.y = y;
        this.sideLength = sideLength;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSideLength() { return this.sideLength; }

    public boolean runsInto(Piece piece) {
        if (this.x == piece.x && this.y == piece.y) {
            return true;
        }

        return false;
    }

    public void drawFigure(Graphics graphics) {
        graphics.setColor(new Color(16744671));
        graphics.fillRect(this.getX(), this.getY(), this.sideLength, this.sideLength);
    }

    @Override
    public String toString() {
        return "(" + this.x + " " + this.y + ")";
    }
}
