package snakegame.gui;

import snakegame.SnakeGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class DrawingBoard extends JPanel {

    private SnakeGame snakeGame;

    public DrawingBoard(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.snakeGame.getIsOver()) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("src", "Game Over.jpg"));
            } catch (Exception e) {
                System.out.println("Couldn't find image " + e.getMessage());
            }
            g.drawImage(image,0,0,null);
        }
        else {
            g.setColor(Color.RED);
            this.snakeGame.getBall().drawFigure(g);

            g.setColor(Color.BLACK);
            this.snakeGame.getSnake().drawFigure(g);
        }

        g.setColor(new Color(3381555));
        String score = "Score: " + this.snakeGame.getSnake().getLength();
        g.drawString(score, this.snakeGame.getWidth() / 2 - score.length() / 2, 10);
    }
}
