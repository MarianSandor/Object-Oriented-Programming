package snakegame.gui;

import snakegame.SnakeGame;
import snakegame.elements.Snake;
import snakegame.movement.KeyboardListener;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {

    private SnakeGame snakeGame;
    private JFrame frame;

    public UserInterface(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
        this.frame = new JFrame("SnakeGame");
    }

    private void createComponents(Container container) {
        DrawingBoard board = new DrawingBoard(this.snakeGame);
        container.add(board);
        this.snakeGame.getUpdatable().addComponent(board);

        this.frame.addKeyListener(new KeyboardListener(this.snakeGame.getSnake()));
    }

    @Override
    public void run() {
        this.frame.setPreferredSize(new Dimension(this.snakeGame.getWidth(), this.snakeGame.getHeight()));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.createComponents(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }
}
