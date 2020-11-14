package car.parts;

import java.io.Serializable;

public class WindShield implements Part, Serializable {

    private String id;
    private int price;

    public WindShield(String id, int price) {
        this.id = id;
        this.price = price;
    }

    public WindShield(int price) {
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
        return "WindShield";
    }

    @Override
    public String toString() {
        return "Windshield ID: " + this.id + " price: " + this.price;
    }
}
