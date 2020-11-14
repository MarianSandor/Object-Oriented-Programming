package brickbreaker.gui;

import brickbreaker.BrickBreakerGame;
import brickbreaker.listener.KeyboardListener;
import brickbreaker.listener.MouseListener;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {

    private BrickBreakerGame brickBreakerGame;
    private JFrame frame;

    public UserInterface(BrickBreakerGame brickBreakerGame) {
        this.brickBreakerGame = brickBreakerGame;
        this.frame = new JFrame("Brick Breaker Game");
    }

    private void createComponents(Container container) {
        DrawingBoard board = new DrawingBoard(this.brickBreakerGame);
        container.add(board);
        this.brickBreakerGame.getUpdatable().addComponent(board);

        this.frame.addMouseMotionListener(new MouseListener(this.brickBreakerGame.getPlatform().getX(), this.brickBreakerGame.getPlatform()));
      //  this.frame.addKeyListener(new KeyboardListener(this.brickBreakerGame.getPlatform()));
    }

    @Override
    public void run() {
        this.frame.setPreferredSize(new Dimension(this.brickBreakerGame.getWidth(), this.brickBreakerGame.getHeight()));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.createComponents(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }
}
