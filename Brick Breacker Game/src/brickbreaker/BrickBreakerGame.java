package brickbreaker;

import brickbreaker.configuration.Level1;
import brickbreaker.elements.Ball;
import brickbreaker.elements.Brick;
import brickbreaker.elements.Direction;
import brickbreaker.elements.Platform;
import brickbreaker.gui.Updatable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrickBreakerGame implements ActionListener {

    private Ball ball;
    private Platform platform;
    private int height;
    private int width;
    private Timer timer;
    private Updatable updatable;
    private Level1 level1;
    private boolean isOver;

    public BrickBreakerGame(int width, int height) {
        this.height = height;
        this.width = width;

        this.platform = new Platform(this.width / 2 - 50, this.height - 50);
        this.ball = new Ball(this.platform.getX() + 50, this.platform.getY() - 200, Direction.DOWN);

        this.timer = new Timer(1, this);
        this.timer.start();

        this.updatable = new Updatable();

        this.level1 = new Level1();

        this.isOver = false;
    }

    public boolean getIsOver() {
        return this.isOver;
    }

    public Ball getBall() {
        return this.ball;
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Updatable getUpdatable() {
        return this.updatable;
    }

    public Level1 getLevel1() {
        return this.level1;
    }

    private void ballCollide() {
        if (this.ball.getY() == 0) {
            this.adjustDirectionAfterCollisionOnX();
        }
        if (this.ball.getX() == 0 || this.ball.getHitBox()[1] == this.width - 15) {
            this.adjustDirectionAfterCollisionOnY();
        }
        if (this.ball.getY() == this.height) {
            this.isOver = true;
        }
    }

    private Brick hitBrick() {
        for (Brick brick : this.getLevel1().getBricks()) {
            if (this.ball.hitBrick(brick)) {
                return brick;
            }
        }

        return null;
    }

    private void adjustDirectionAfterPlatform() {
        int position = this.ball.getX() - this.platform.getX();

        if (position < 30) {
            this.ball.setDirection(Direction.UP_LEFT);
        } else if (position <= 70) {
            this.adjustDirectionAfterCollisionOnX();
        } else if (position < 100) {
            this.ball.setDirection(Direction.UP_RIGHT);
        }
    }

    private void adjustDirectionAfterCollisionOnX() {
        Direction newDirection = Direction.DOWN;

        if (this.ball.getDirection() == Direction.DOWN) {
            newDirection = Direction.UP;
        }
        if (this.ball.getDirection() == Direction.UP) {
            newDirection = Direction.DOWN;
        }
        if (this.ball.getDirection() == Direction.UP_LEFT) {
            newDirection = Direction.DOWN_LEFT;
        }
        if (this.ball.getDirection() == Direction.UP_RIGHT) {
            newDirection = Direction.DOWN_RIGHT;
        }
        if (this.ball.getDirection() == Direction.DOWN_LEFT) {
            newDirection = Direction.UP_LEFT;
        }
        if (this.ball.getDirection() == Direction.DOWN_RIGHT) {
            newDirection = Direction.UP_RIGHT;
        }

        this.ball.setDirection(newDirection);
    }

    private void adjustDirectionAfterCollisionOnY() {
        Direction newDirection = Direction.DOWN;

        if (this.ball.getDirection() == Direction.DOWN) {
            newDirection = Direction.UP;
        }
        if (this.ball.getDirection() == Direction.UP) {
            newDirection = Direction.DOWN;
        }
        if (this.ball.getDirection() == Direction.UP_LEFT) {
            newDirection = Direction.UP_RIGHT;
        }
        if (this.ball.getDirection() == Direction.UP_RIGHT) {
            newDirection = Direction.UP_LEFT;
        }
        if (this.ball.getDirection() == Direction.DOWN_LEFT) {
            newDirection = Direction.DOWN_RIGHT;
        }
        if (this.ball.getDirection() == Direction.DOWN_RIGHT) {
            newDirection = Direction.DOWN_LEFT;
        }

        this.ball.setDirection(newDirection);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.ball.move();

        if (this.ball.hitPlatform(this.platform)) {
            this.adjustDirectionAfterPlatform();
        }

        if (this.hitBrick() != null) {

            if ((this.ball.getHitBox()[1] == this.hitBrick().getHitBox()[0] || this.ball.getHitBox()[0] == this.hitBrick().getHitBox()[1]) &&
                (this.ball.getHitBox()[2] >= this.hitBrick().getHitBox()[2] - this.ball.getLength() / 2 && this.ball.getHitBox()[3] <= this.hitBrick().getHitBox()[3] + this.ball.getLength() / 2)) {
                this.adjustDirectionAfterCollisionOnY();
            } else {
                this.adjustDirectionAfterCollisionOnX();
            }

            this.level1.distroy(hitBrick());
        }

        this.ballCollide();

        this.updatable.update();

        this.timer.setDelay(1);
    }
}
