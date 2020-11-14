/** Person class
 *
 *  -defines a person that is identified by its name (String) and phone number (String).
 */
package car.owner;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String phoneNumber;

    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String toString() {
        return "Name " + this.name + " Phone Number: " + this.phoneNumber;
    }
}
