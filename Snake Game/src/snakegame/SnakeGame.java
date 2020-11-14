package snakegame;

import snakegame.elements.Ball;
import snakegame.elements.Piece;
import snakegame.elements.Snake;
import snakegame.gui.Updatable;
import snakegame.movement.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SnakeGame implements ActionListener {

    private Snake snake;
    private Ball ball;
    private Timer timer;
    private int width;
    private int height;
    private int sideLength;
    private boolean isOver;
    private Updatable updatable;

    public SnakeGame(int width, int height, int sideLength) {
        this.timer = new Timer(1000, this);
        this.timer.start();

        this.updatable = new Updatable();

        this.isOver = false;

        this.height = height;
        this.width = width;
        this.sideLength = sideLength;

        this.snake = new Snake(width / 2, height / 2, this.sideLength, Direction.DOWN);
        this.ball = new Ball(0,0, this.sideLength);
        this.respawnBall();
    }

    private void respawnBall() {
        Random random = new Random();

        this.ball.setX(random.nextInt(this.width / (this.ball.getRaze() + 1)) * this.ball.getRaze());
        this.ball.setY(random.nextInt(this.height / (this.ball.getRaze() + 1)) * this.ball.getRaze());
    }

    private boolean hitWall() {

        for (Piece piece : this.snake.getPieces()) {
            if (piece.getX() >= this.width - this.sideLength || piece.getX() < 0 || piece.getY() >= this.height - this.sideLength || piece.getY() < 0) {
                return true;
            }
        }

        return false;
    }

    public Ball getBall() {
        return this.ball;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean getIsOver() {
        return this.isOver;
    }

    public Updatable getUpdatable() {
        return this.updatable;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.snake.move();

        if (this.snake.runsInto(this.ball)) {
            this.snake.grow();
            this.respawnBall();
        }

        if (this.snake.runsIntoItself() || this.hitWall()) {
            this.isOver = true;
        }

        this.updatable.update();

        this.timer.setDelay(1000 / this.getSnake().getLength());
    }
}
