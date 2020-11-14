package car.parts;

import java.io.Serializable;

public class Engine implements Part, Serializable {

    private String id;
    private int price;

    public Engine(String id, int price) {
        this.id = id;
        this.price = price;
    }

    public Engine(int price) {
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
        return "Engine";
    }

    @Override
    public String toString() {
        return "Engine ID: " + this.id + " price: " + this.price;
    }
}
