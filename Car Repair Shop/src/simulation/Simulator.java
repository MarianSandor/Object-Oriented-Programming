/** Simulation class
 *
 *  -unites all the classes that are related to the repair shop.
 *  -generates random data and simulates the activity of the car repair shop.
 */

package simulation;

import car.Car;
import car.owner.Person;
import gui.Updatable;
import repairShop.CarRepairShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulator implements ActionListener, Serializable {

    private CarRepairShop carRepairShop;
    private ArrayList<Car> cars;
    private Updatable updatable;
    private Clock clock;
    private Timer timer;
    private File file;

    public Simulator () {
        this.Deserialize();
        this.carRepairShop.setClock(this.clock);
        this.updatable = new Updatable();
        this.file = new File("ProfitRecord.csv");

        this.timer = new javax.swing.Timer(10, this);
        this.timer.start();
    }

    private void Deserialize () {
        File simulatorSer = new File("./simulator.ser");
        boolean exists = simulatorSer.exists();
        this.carRepairShop = null;
        this.cars = null;
        this.clock = null;

        if(exists)
        {
            try {
                FileInputStream file = new FileInputStream("./simulator.ser");
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                this.carRepairShop = (CarRepairShop) in.readObject();
                this.cars = (ArrayList<Car>) in.readObject();
                this.clock = (Clock) in.readObject();

                in.close();
                file.close();
            }
            catch (IOException e) {
                System.out.println("IOException is caught");
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            this.carRepairShop = new CarRepairShop(8, 12);
            this.cars = new ArrayList<Car>();
            this.clock = new Clock();
        }
    }

    private void Serialize() {
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("simulator.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this.carRepairShop);
            out.writeObject(this.cars);
            out.writeObject(this.clock);


            out.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            System.out.println(ex);
        }
    }

    public Clock getClock() {
        return this.clock;
    }

    public CarRepairShop getCarRepairShop() {
        return this.carRepairShop;
    }

    public Updatable getUpdatable() {
        return this.updatable;
    }

    private void simulate() {
        this.clock.advance();

        if (this.clock.hours >= 8 && this.clock.hours < 20) {
            this.carRepairShop.updateTime();

            Random random = new Random();
            double p = random.nextDouble();
            int minute = random.nextInt(60);

            if (this.clock.minutes == minute) {
                if (p >= 0.70) {
                    Person person = new Person(Generator.name(), Generator.phoneNumber());
                    Car car = new Car(person, Generator.numberPlate());
                    this.cars.add(car);

                }

                if (p >= 0.65) {
                    carRepairShop.updateStock();
                }
            }

            if (this.clock.minutes == 0) {
                this.carRepairShop.updateStatus();
                this.carRepairShop.updateDoneCars();

                if (!this.cars.isEmpty()) {
                    if (this.carRepairShop.receiveCar(cars.get(0))) {
                        this.cars.remove(0);
                    }
                }
            }
        } else if (this.clock.hours == 20 && this.clock.minutes == 0) {

                try (FileWriter writer = new FileWriter(this.file, true)) {

                    StringBuilder sb = new StringBuilder();

                    sb.append(this.clock.date());
                    sb.append(',');
                    sb.append(this.carRepairShop.getSold());
                    sb.append('\n');

                    writer.write(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            this.carRepairShop.initSold();
        }
    }

    public void drawTimeAndQueue(Graphics graphics) {
        Font title = new Font( Font.MONOSPACED, Font.BOLD, 25);
        Font label = new Font( Font.MONOSPACED, Font.BOLD, 15);

        graphics.setFont(title);
        graphics.drawString(this.clock.date() + "  " + this.clock.toString(), 600, 50);

        if (this.clock.hours >= 8 && this.clock.hours <= 20) {
            graphics.setColor(Color.GREEN);
            graphics.drawString("Open", 150, 40);
        } else {
            graphics.setColor(Color.RED);
            graphics.drawString("Closed", 150, 40);
        }

        int x = 1030;
        int y = 700;
        int width = 125;
        int height = 40;

        graphics.setFont(label);

        for (int i = 0; i < this.cars.size(); i++) {
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(x + i * 175, y, width, height);
            graphics.setColor(Color.BLACK);
            graphics.drawString(this.cars.get(i).getLicensePlate(), x + i * 175 + 20, y + height / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       this.simulate();
       this.updatable.update();
       this.Serialize();

       this.timer.setDelay(10);
    }
}
