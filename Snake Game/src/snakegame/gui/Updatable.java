package snakegame.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Updatable {

    private List<Component> components;

    public Updatable() {
        this.components = new ArrayList<Component>();
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public void update() {
        for (Component component : this.components) {
            component.repaint();
        }
    }
}
