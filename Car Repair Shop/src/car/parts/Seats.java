package car.parts;

import java.io.Serializable;

public class Seats implements Part, Serializable {

    private String id;
    private int price;

    public Seats(String id, int price) {
        this.id = id;
        this.price = price;
    }

    public Seats(int price) {
        this("XXXX", price);
    }

    public String getId() {
        return this.id;
    }

    public int getPrice() {
        return this.price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return "Seats";
    }

    @Override
    public String toString() {
        return "Seats ID: " + this.id + " price: " + this.price;
    }
}
