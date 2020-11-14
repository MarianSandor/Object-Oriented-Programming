package gui;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Updatable implements Serializable {

    private  ArrayList<Component> components;

    public Updatable() {
        this.components = new ArrayList<Component>();
    }

    public void addComponent (Component component) {
        this.components.add(component);
    }

    public void update() {
        for (Component component : this.components) {
            component.repaint();
        }
    }
}
