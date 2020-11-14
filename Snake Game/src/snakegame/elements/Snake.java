package snakegame.elements;

import snakegame.movement.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<Piece> pieces;
    private int x;
    private int y;
    private int sideLength;
    private Direction direction;
    private boolean toGrow;

    public Snake(int originalX, int originalY, int sideLength, Direction originalDirection) {
        this.direction = originalDirection;
        this.x = originalX;
        this.y = originalY;
        this.sideLength = sideLength;
        this.toGrow = false;

        this.pieces = new ArrayList<Piece>();
        this.pieces.add(new Piece(this.x, this.y, this.sideLength));
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public int getLength() {
        return this.pieces.size();
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    private void setNewCoordinates() {
        if (this.direction == Direction.DOWN) {
            this.y += this.sideLength;
        }
        if (this.direction == Direction.UP) {
            this.y -= this.sideLength;
        }
        if (this.direction == Direction.LEFT) {
            this.x -= this.sideLength;
        }
        if (this.direction == Direction.RIGHT) {
            this.x += this.sideLength;
        }
    }

    public void move() {
        this.setNewCoordinates();

        this.pieces.add(this.pieces.size(), new Piece(this.x, this.y, this.sideLength));

        if (!this.toGrow && this.pieces.size() > 3) {
            this.pieces.remove(0);
        }

        this.toGrow = false;
    }

    public void grow() {
        this.toGrow = true;
    }

    public boolean runsInto(Ball ball) {
        for (Piece snakePiece : this.pieces) {
            if (snakePiece.getX() == ball.getX() && snakePiece.getY() == ball.getY()) {
                return true;
            }
        }

        return false;
    }

    public boolean runsIntoItself() {

        for (int i = 0; i < this.pieces.size() - 1; i++) {
            for (int j = i + 1; j < this.pieces.size(); j++) {
                if (this.pieces.get(i).getY() == this.pieces.get(j).getY() && this.pieces.get(i).getX() == this.pieces.get(j).getX()) {
                    return true;
                }
            }
        }

        return false;
    }

    public void drawFigure(Graphics g) {
        for (Piece piece : this.pieces) {
            piece.drawFigure(g);
        }
    }
}
