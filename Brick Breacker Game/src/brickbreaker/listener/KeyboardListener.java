package brickbreaker.listener;

import brickbreaker.elements.Direction;
import brickbreaker.elements.Platform;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private Platform platform;

    public KeyboardListener(Platform platform) {
        this.platform = platform;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            this.platform.move(Direction.LEFT);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.platform.move(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
