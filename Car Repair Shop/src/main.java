import car.Car;
import car.owner.Person;
import car.parts.Doors;
import car.parts.Engine;
import car.parts.WindShield;
import gui.UserInterface;
import repairShop.CarRepairShop;
import repairShop.Stock;
import simulation.Simulator;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class main {
    public static void main(String[] args) {

        Simulator simulator = new Simulator();
        UserInterface ui = new UserInterface(simulator, 1600, 900);

        SwingUtilities.invokeLater(ui);
    }
}
