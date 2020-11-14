/** Stock class
 *
 *  -defines the storage of the parts available in the workshop.
 */


package repairShop;

import car.parts.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Stock implements Serializable {

    private ArrayList<Engine> engines;
    private ArrayList<GearBox> gearBoxes;
    private ArrayList<WindShield> windShields;
    private ArrayList<Seats> seats;
    private ArrayList<Doors> doors;
    private ArrayList<Tires> tires;

    public Stock() {
        this.doors = new ArrayList<Doors>();
        this.engines = new ArrayList<Engine>();
        this.gearBoxes = new ArrayList<GearBox>();
        this.seats = new ArrayList<Seats>();
        this.tires = new ArrayList<Tires>();
        this.windShields = new ArrayList<WindShield>();
    }

    public void addPart(Part part) {
        if (part instanceof Engine) {
            this.engines.add((Engine) part);
        }
        else if (part instanceof Doors) {
            this.doors.add((Doors) part);
        }
        else if (part instanceof GearBox) {
            this.gearBoxes.add((GearBox) part);
        }
        else if (part instanceof Seats) {
            this.seats.add((Seats) part);
        }
        else if (part instanceof Tires) {
            this.tires.add((Tires) part);
        }
        else if (part instanceof WindShield) {
            this.windShields.add((WindShield) part);
        }
    }

    public Part usePart(Class<?> part) {
        Part replacementPart = null;

        if (part == Engine.class) {
            replacementPart = this.engines.get(0);
            this.engines.remove(0);
        }
        else if (part == Doors.class) {
            replacementPart = this.doors.get(0);
            this.doors.remove(0);
        }
        else if (part == GearBox.class) {
            replacementPart = this.gearBoxes.get(0);
            this.gearBoxes.remove(0);
        }
        else if (part == Seats.class) {
            replacementPart = this.seats.get(0);
            this.seats.remove(0);
        }
        else if (part == Tires.class) {
            replacementPart = this.tires.get(0);
            this.tires.remove(0);
        }
        else if (part == WindShield.class) {
            replacementPart = this.windShields.get(0);
            this.windShields.remove(0);
        }

        return replacementPart;
    }

    public boolean checkStock(Class<?> part) {
        if (part == Engine.class && !this.engines.isEmpty()) {
            return true;
        }
        else if (part == Doors.class && !this.doors.isEmpty()) {
            return true;
        }
        else if (part == GearBox.class && !this.gearBoxes.isEmpty()) {
            return true;
        }
        else if (part == Seats.class && !this.seats.isEmpty()) {
            return true;
        }
        else if (part == Tires.class && !this.tires.isEmpty()) {
            return true;
        }
        else if (part == WindShield.class && !this.windShields.isEmpty()) {
            return true;
        }

        return false;
    }

    public int getPrice(Class<?> part) {
        if (part == Engine.class) {
            return this.engines.get(0).getPrice();
        }
        else if (part == Doors.class) {
            return this.doors.get(0).getPrice();
        }
        else if (part == GearBox.class) {
            return this.gearBoxes.get(0).getPrice();
        }
        else if (part == Seats.class) {
            return this.seats.get(0).getPrice();
        }
        else if (part == Tires.class) {
            return this.tires.get(0).getPrice();
        }
        else if (part == WindShield.class) {
            return this.windShields.get(0).getPrice();
        }

        return -1;
    }

    public void drawStock(int x, int y, int width, int height, Graphics graphics, Font f) {
        graphics.setFont(f);

        graphics.setColor(Color.BLUE);
        graphics.fillRoundRect(x, y, width, height, 20, 20);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Doors: " + this.doors.size(), x + 10, y + height / 2);
        y = y + height + 10;

        graphics.setColor(Color.BLUE);
        graphics.fillRoundRect(x, y, width, height, 20, 20);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Engines: " + this.engines.size(), x + 10, y + height / 2);
        y = y + height + 10;

        graphics.setColor(Color.BLUE);
        graphics.fillRoundRect(x, y, width, height, 20, 20);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Gearboxes: " + this.gearBoxes.size(), x + 10, y + height / 2);
        y = y + height + 10;

        graphics.setColor(Color.BLUE);
        graphics.fillRoundRect(x, y, width, height, 20, 20);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Seats: " + this.seats.size(), x + 10, y + height / 2);
        y = y + height + 10;

        graphics.setColor(Color.BLUE);
        graphics.fillRoundRect(x, y, width, height, 20, 20);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Tires: " + this.tires.size(), x + 10, y + height / 2);
        y = y + height + 10;

        graphics.setColor(Color.BLUE);
        graphics.fillRoundRect(x, y, width, height, 20, 20);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Windshields: " + this.windShields.size(), x + 10, y + height / 2);

    }

    @Override
    public String toString() {
        String toPrint = "";

        toPrint += "Doors: ";
        toPrint += this.doors.size();
        toPrint += "\n";

        toPrint += "Engines: ";
        toPrint += this.engines.size();
        toPrint += "\n";

        toPrint += "Gearboxes: ";
        toPrint += this.gearBoxes.size();
        toPrint += "\n";

        toPrint += "Seats: ";
        toPrint += this.seats.size();
        toPrint += "\n";

        toPrint += "Tires: ";
        toPrint += this.tires.size();
        toPrint += "\n";

        toPrint += "Windshields: ";
        toPrint += this.windShields.size();
        toPrint += "\n";

        return toPrint;
    }
}
