import snakegame.SnakeGame;
import snakegame.gui.UserInterface;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(800, 800, 20);
        UserInterface ui = new UserInterface(snakeGame);
        SwingUtilities.invokeLater(ui);
    }
}
