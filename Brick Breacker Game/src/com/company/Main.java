package com.company;

import brickbreaker.BrickBreakerGame;
import brickbreaker.gui.UserInterface;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        BrickBreakerGame brickBreakerGame = new BrickBreakerGame(1000, 800);
        UserInterface ui = new UserInterface(brickBreakerGame);
        SwingUtilities.invokeLater(ui);
  
    }
}
