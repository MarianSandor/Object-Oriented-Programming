package brickbreaker.gui;

import brickbreaker.BrickBreakerGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class DrawingBoard extends JPanel {

    private BrickBreakerGame brickBreakerGame;

    public DrawingBoard(BrickBreakerGame brickBreakerGame) {
        this.brickBreakerGame = brickBreakerGame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.brickBreakerGame.getIsOver()) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("src", "Game Over.jpg"));
            } catch (Exception e) {
                System.out.println("Couldn't find image " + e.getMessage());
            }
            g.drawImage(image,0,0,null);
        } else if (this.brickBreakerGame.getLevel1().IsWin()) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("src", "Win.png"));
            } catch (Exception e) {
                System.out.println("Couldn't find image " + e.getMessage());
            }
            g.drawImage(image,0,0,null);
        } else {
            this.brickBreakerGame.getBall().drawBall(g);
            this.brickBreakerGame.getPlatform().drawPlatform(g);
            this.brickBreakerGame.getLevel1().drawLevel(g);
        }
    }
}
