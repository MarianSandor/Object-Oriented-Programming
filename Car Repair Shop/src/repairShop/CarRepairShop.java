/** CarRepairShop class
 *
 *  -the object of this class type is a car repair shop storing all the needed data to run.
 *  -it has the stock (Stock), capacity of the yard and workshop, the sold and the registrations.
 */

package repairShop;

import car.Car;
import car.parts.*;
import gui.Updatable;
import simulation.Clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import java.util.Random;

public class CarRepairShop implements Serializable {

    private int sold;
    private Clock clock;
    private Stock stock;
    private int workshopCapacity;
    private int yardCapacity;
    private ArrayList<Registration> registrationsInWorkshop;
    private ArrayList<Registration> registrationsInYard;
    private ArrayList<Registration> registrationsDone;
    private HashMap<String, ArrayList<String>> carRecord;

    public CarRepairShop(int workshopCapacity, int yardCapacity) {
        this.sold = 0;
        this.stock = new Stock();
        this.workshopCapacity = workshopCapacity;
        this.yardCapacity = yardCapacity;
        this.registrationsInWorkshop = new ArrayList<Registration>();
        this.registrationsInYard = new ArrayList<Registration>();
        this.registrationsDone = new ArrayList<Registration>();
        this.carRecord = new HashMap<String, ArrayList<String>>();

        this.initializeStock();
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public boolean hasFreeSpaceYard() {
        if (this.registrationsInYard.size() < this.yardCapacity) {
            return true;
        }

        return false;
    }

    public boolean hasFreeSpaceWorkshop() {
        if (this.registrationsInWorkshop.size() < this.workshopCapacity) {
            return true;
        }

        return false;
    }

    private void moveCarToDone(Registration registration) {
        this.registrationsDone.add(registration);
        this.registrationsInWorkshop.remove(registration);
        this.carRecord.get(registration.getCar().getLicensePlate()).add(this.clock.date() + " " + this.clock.toString());
    }

    public void updateDoneCars() {
        if (!this.registrationsDone.isEmpty()) {
            for (Registration registration : this.registrationsDone) {
                System.out.println(registration.getCar().getOwner());
                this.sold += registration.getTotal();

                try (FileWriter writer = new FileWriter("Car Record.csv", true)) {

                    StringBuilder sb = new StringBuilder();

                    sb.append(registration.getCar().getLicensePlate());
                    sb.append(',');
                    sb.append(this.carRecord.get(registration.getCar().getLicensePlate()).get(0));
                    sb.append(',');
                    sb.append(this.carRecord.get(registration.getCar().getLicensePlate()).get(1));
                    sb.append('\n');

                    writer.write(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            this.registrationsDone.removeAll(this.registrationsDone);
        }
    }

    public boolean receiveCar(Car car) {
        if (this.hasFreeSpaceYard()) {
            this.registrationsInYard.add(new Registration(car));
            if (!this.carRecord.containsKey(car.getLicensePlate())) {
                this.carRecord.put(car.getLicensePlate(), new ArrayList<String>());
                this.carRecord.get(car.getLicensePlate()).add(this.clock.date() + " " + this.clock.toString());
            }
            return true;
        }

        return false;
    }

    private void moveCarInWorkshop(Registration registration) {
        if (this.hasFreeSpaceWorkshop()) {
            this.registrationsInWorkshop.add(registration);
            this.registrationsInYard.remove(registration);
        }
    }

    private void initializeStock() {
        for (int i = 0; i < 10; i++) {
            this.updateStock();
        }
    }

    public void updateStock() {
        Random random = new Random();
        int number = random.nextInt(6);
        int price = 100 + random.nextInt(200);

        switch (number) {
            case 0:
                this.stock.addPart(new Doors(price));
                break;
            case 1:
                this.stock.addPart(new Engine(price));
                break;
            case 2:
                this.stock.addPart(new GearBox(price));
                break;
            case 3:
                this.stock.addPart(new Seats(price));
                break;
            case 4:
                this.stock.addPart(new Tires(price));
                break;
            case 5:
                this.stock.addPart(new WindShield(price));
                break;
        }
    }

    private Class<?> diagnose() {
        Random random = new Random();
        int number = random.nextInt(6);
        Class<?> part = Engine.class;

        switch (number) {
            case 0:
                part = Doors.class;
                break;
            case 1:
                part = Engine.class;
                break;
            case 2:
                part = GearBox.class;
                break;
            case 3:
                part = Seats.class;
                break;
            case 4:
                part = Tires.class;
                break;
            case 5:
                part = WindShield.class;
                break;
        }

        return part;
    }

    public void updateStatus() {
        ArrayList<Registration> carToWorkshop = new ArrayList<Registration>();
        ArrayList<Registration> carDone = new ArrayList<Registration>();


        for (Registration registration : this.registrationsInWorkshop) {
            if (registration.getStatus() == Status.Ready) {
                carDone.add(registration);
            }
        }

        for (Registration registration : this.registrationsInYard) {
            if (registration.getStatus() == Status.WaitingForDiagnose) {
                Class<?> part = this.diagnose();

                registration.setReplacementPart(part);
                registration.updateStatus(Status.Diagnosed);
            }
            else if (registration.getStatus() == Status.Diagnosed) {
                if (!this.stock.checkStock(registration.getPart())) {
                    registration.updateStatus(Status.WaitingForParts);
                }
                else {
                    registration.setReplacementPart(this.stock.usePart(registration.getPart()));
                    registration.updateStatus(Status.ReadyToRepair);
                }
            }
            else if (registration.getStatus() == Status.WaitingForParts) {
                if (this.stock.checkStock(registration.getPart())) {
                    registration.setReplacementPart(this.stock.usePart(registration.getPart()));
                    registration.updateStatus(Status.ReadyToRepair);
                }
            }
            else if (registration.getStatus() == Status.ReadyToRepair) {
                carToWorkshop.add(registration);
            }
        }

        for (Registration registration : carToWorkshop) {
            if (this.hasFreeSpaceWorkshop()) {
                registration.updateStatus(Status.InRepair);
                registration.setTime();
                this.moveCarInWorkshop(registration);
            }
        }

        for (Registration registration : carDone) {
            this.moveCarToDone(registration);
        }
    }

    public void updateTime() {
        for (Registration registration : this.registrationsInWorkshop) {
            registration.oneMinute();
        }
    }

    public int getSold() {
        return this.sold;
    }

    public void initSold() { this.sold = 0; }

    public void drawCarShopRepair(Graphics graphics) {
        int x = 0;
        int y = 80;
        int width = 200;
        int height = 60;

        Graphics2D g2 = (Graphics2D) graphics;
        Font label = new Font( Font.MONOSPACED, Font.BOLD, 15);
        Font title = new Font( Font.MONOSPACED, Font.BOLD, 25);


        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Float(width + 10, y - 10, width + 10, 900));
        g2.draw(new Line2D.Float(2 * width + 30, y - 10, 2 * width + 30, 900));

        g2.setStroke(new BasicStroke(10));
        g2.draw(new Line2D.Float(0, y - 10, 1600, y - 10));
        g2.draw(new Line2D.Float(5 * width + 10, y - 10, 5 * width + 10, 900));
        g2.draw(new Line2D.Float(5 * width + 10, 8 * y, 1600, 8 * y));

        graphics.setColor(Color.BLACK);
        graphics.setFont(title);
        graphics.drawString("Current Profit: " + this.sold, 600, 20);
        graphics.drawString("Status: ", 30, 40);
        graphics.drawString("Done Cars", x + 40, y + 20);
        graphics.drawString("Cars In", x + width + 60, y + 20);
        graphics.drawString("Workshop", x + width + 60, y + 40);
        graphics.drawString("Cars In Yard", x + 3 * width  + 20, y + 20);
        graphics.drawString("Stock", x + 6 * width + 30, y + 20);
        graphics.drawString("Queue", x + 6 * width + 30, 8 * y + 30);


        for (int i = 0; i < this.workshopCapacity; i++) {
            graphics.setColor(Color.GREEN);
            graphics.fillRect(x + width + 20, (i + 1) * y + y , width, height);

            if (i < this.registrationsInWorkshop.size()) {
                this.registrationsInWorkshop.get(i).drawRegistration(x + width + 20, (i + 1) * y + y, width, height, graphics, label);
            }
        }

        int index = 0;
        for (int i = 0; i < this.yardCapacity; i++) {
            if (i < this.yardCapacity / 2) {
                x = 2 * width + 40;
            } else {
                x = 4 * width;
            }

            if (index == this.yardCapacity / 2) {
                index = 0;
            }
            graphics.setColor(Color.GREEN);
            graphics.fillRect(x, (index + 1) * y + y, width, height);

            if (i < this.registrationsInYard.size()) {
                this.registrationsInYard.get(i).drawRegistration(x, (index + 1) * y + y, width, height, graphics, label);
            }

            index++;
        }

        x = 6 * width - 30;
        y = 2 * y;

        this.stock.drawStock(x, y, width, height, graphics, label);
    }

    @Override
    public String toString() {
        String inWorkshop = "";
        String inYard = "";

        for (Registration registration : this.registrationsInWorkshop) {
            inWorkshop += registration;
            inWorkshop += "\n";
        }
        for (Registration registration : this.registrationsInYard) {
            inYard += registration;
            inYard += "\n";
        }

        return " Profit: " + this.sold + "\n Stock: \n" + this.stock + " Cars in workshop\n" + inWorkshop + " Cars in yard\n" + inYard;
    }
}
