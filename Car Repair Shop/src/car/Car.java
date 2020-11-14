/** Car class
 * -defines a car that the repair shop can receive.
 * -each car is characterized by the person that owns tge car (Person) and its license plate (String).
 */

package car;
import car.owner.Person;

import java.io.Serializable;

public class Car implements Serializable {

    private Person owner;
    private String licensePlate;

    public Car(Person owner, String licensePlate) {
        this.owner = owner;
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public Person getOwner() {
        return this.owner;
    }

    @Override
    public String toString() {
        return this.owner + " License Plate: " + this.licensePlate;
    }
}
