package brickbreaker.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Updatable {

    private List<Component> componentList;

    public Updatable() {
        this.componentList = new ArrayList<Component>();
    }

    public void addComponent(Component component) {
        this.componentList.add(component);
    }

    public void update() {
        for (Component component : this.componentList) {
            component.repaint();
        }
    }
}
