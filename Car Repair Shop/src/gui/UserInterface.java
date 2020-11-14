package gui;

import repairShop.CarRepairShop;
import simulation.Simulator;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class UserInterface implements Runnable, Serializable {

    private Simulator simulator;
    private JFrame frame;
    private DrawingBoard board;
    private int width;
    private int height;

    public UserInterface (Simulator simulator, int width, int height) {
        this.simulator = simulator;
        this.width = width;
        this.height = height;
        this.frame = new JFrame("Car Repair Shop");
        this.board = new DrawingBoard(this.simulator);
    }

    public Simulator getSimulator() {
        return this.simulator;
    }

    private void createComponents (Container container) {
        container.add(board);

        this.simulator.getUpdatable().addComponent(board);
    }

    @Override
    public void run() {
        this.frame.setPreferredSize(new Dimension(this.width, this.height));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.createComponents(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }
}
