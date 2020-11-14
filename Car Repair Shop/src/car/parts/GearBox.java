package car.parts;

import java.io.Serializable;

public class GearBox implements Part, Serializable {

    private String id;
    private int price;

    public GearBox(String id, int price) {
        this.id = id;
        this.price = price;
    }

    public GearBox(int price) {
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
        return "Gearbox";
    }

    @Override
    public String toString() {
        return "GearBox ID: " + this.id + " price: " + this.price;
    }
}
