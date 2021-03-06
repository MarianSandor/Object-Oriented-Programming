package car.parts;

import java.io.Serializable;

public class Tires implements Part, Serializable {

    private String id;
    private int price;

    public Tires(String id, int price) {
        this.id = id;
        this.price = price;
    }

    public Tires(int price) {
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
        return "Tires";
    }

    @Override
    public String toString() {
        return "Tires ID: " + this.id + " price: " + this.price;
    }
}
