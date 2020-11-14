/** Registration class
 *
 *  -defines a registration for the repair shop that holds a car (Car), the status of if (Status) and the part that need to be replaced (Part)
 *  -keeping the data related to a car stored in a class like this one makes working with them very easy.
 */


package repairShop;

import car.Car;
import car.parts.*;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Registration implements Serializable {
    private Car car;
    private Status status;
    private int minutes;
    private Part replacementPart;
    private Class<?> part;

    public Registration(Car car) {
        this.car = car;
        this.minutes = -1;
        this.status = Status.WaitingForDiagnose;
    }

    public Car getCar() {
        return this.car;
    }

    public Class<?> getPart() {
        return this.part;
    }

    public Status getStatus()  {
        return this.status;
    }

    public int getTotal() {
        Random random = new Random();

        return this.replacementPart.getPrice() + 10 + random.nextInt(40);
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void setTime() {
        Random random = new Random();
        this.minutes = 600 + random.nextInt(2400);
    }

    public void setReplacementPart(Part replacementPart) {
        this.replacementPart = replacementPart;
    }

    public void oneMinute() {
        if (this.minutes != -1) {
            if (this.minutes > 0) {
                this.minutes--;
            }
            if (this.minutes == 0) {
                this.minutes = -1;
                this.status = Status.Ready;
            }
        }
    }

    public void setReplacementPart(Class<?> part) {
        this.part = part;
    }

    public void drawRegistration (int x, int y, int width, int height, Graphics graphics , Font f) {
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y , width, height);

        if (this.status == Status.Ready) {
            graphics.setColor(Color.GREEN);
            graphics.fillRect(0, y , width, height);
        }
        graphics.setColor(Color.BLACK);
        graphics.setFont(f);

        graphics.drawString(this.car.getLicensePlate(), x, y + height * 1 /4);
        graphics.drawString(this.status.toString(), x, y + height * 2 / 4);

        if (this.status == Status.Ready) {
            graphics.drawString(this.car.getOwner().getName(), 0, y + height * 1 /4);
            graphics.drawString(this.car.getOwner().getPhoneNumber(), 0, y + height * 2 / 4);
            graphics.drawString(this.car.getLicensePlate(), 0, y + height * 3 / 4);
        }

        if (this.status == Status.InRepair) {
            graphics.drawString("Repair time: " + (this.minutes / 60 + 1), x, y + height * 3 / 4);
        }

        if (this.status == Status.WaitingForParts) {
            graphics.drawString("Part: " + this.getPartName(), x, y + height * 3 / 4);
        }
    }

    private String getPartName() {
        String partName = "";

        if (this.part == Engine.class) {
            partName = "Engine";
        }
        else if (this.part == Doors.class) {
            partName = "Doors";
        }
        else if (this.part == GearBox.class) {
            partName = "GearBox";
        }
        else if (this.part == Seats.class) {
            partName = "Seats";
        }
        else if (this.part == Tires.class) {
            partName = "Tires";
        }
        else if (this.part == WindShield.class) {
            partName = "WindShield";
        }

        return partName;
    }

    @Override
    public String toString() {
        String sparePart = this.getPartName();

        return this.car + " Status: " + this.status + " Replacement Part: " + sparePart + " Repair Time: " + (this.minutes / 60 + 1);
    }
}
