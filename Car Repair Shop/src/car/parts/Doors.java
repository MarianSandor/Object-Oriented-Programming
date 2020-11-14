package car.parts;

import java.io.Serializable;

public class Doors implements Part, Serializable {

    private String id;
    private int price;

    public Doors(String id, int price) {
        this.id = id;
        this.price = price;
    }

    public Doors(int price) {
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
        return "Doors";
    }

    @Override
    public String toString() {
        return "Doors ID: " + this.id + " price: " + this.price;
    }
}
