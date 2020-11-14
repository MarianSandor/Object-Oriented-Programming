package gui;

import repairShop.CarRepairShop;
import simulation.Simulator;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class DrawingBoard extends JPanel implements Serializable {

    private Simulator simulator;

    public DrawingBoard (Simulator simulator) {
        this.simulator = simulator;
    }

    @Override
    protected  void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.simulator.getCarRepairShop().drawCarShopRepair(g);
        this.simulator.drawTimeAndQueue(g);
    }
}
