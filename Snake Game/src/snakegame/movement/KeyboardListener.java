package snakegame.movement;

import snakegame.elements.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private Snake snake;

    public KeyboardListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            if (this.snake.getDirection() != Direction.RIGHT) {
                this.snake.setDirection(Direction.LEFT);
            }
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (this.snake.getDirection() != Direction.LEFT) {
                this.snake.setDirection(Direction.RIGHT);
            }
        }
        if (keyEvent.getKeyCode() == keyEvent.VK_UP) {
            if (this.snake.getDirection() != Direction.DOWN) {
                this.snake.setDirection(Direction.UP);
            }
        }
        if (keyEvent.getKeyCode() == keyEvent.VK_DOWN) {
            if (this.snake.getDirection() != Direction.UP) {
                this.snake.setDirection(Direction.DOWN);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
