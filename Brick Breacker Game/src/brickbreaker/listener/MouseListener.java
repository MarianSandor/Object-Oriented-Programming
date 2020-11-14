package brickbreaker.listener;

import brickbreaker.elements.Direction;
import brickbreaker.elements.Platform;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListener implements MouseMotionListener {

    private int oldX;
    private Platform platform;

    public MouseListener(int x, Platform platform) {
        this.oldX = x;
        this.platform = platform;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (mouseEvent.getX() > oldX) {
            this.platform.move(Direction.RIGHT);
        }
        if (mouseEvent.getX() < oldX) {
            this.platform.move(Direction.LEFT);
        }

        this.oldX = mouseEvent.getX();
    }
}
