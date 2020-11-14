package brickbreaker.configuration;

import brickbreaker.elements.Brick;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level1 {

    private List<Brick> bricks;
    private boolean Win;

    public Level1() {
        this.bricks = new ArrayList<Brick>();
        this.Win = false;

        this.createConfiguration();
    }

    public boolean IsWin(){
        return this.Win;
    }

    private void createConfiguration() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                Brick brick = new Brick(100 + j * 80, 50 + i * 50 );
                this.bricks.add(brick);
            }
        }
    }

    public List<Brick> getBricks() {
        return this.bricks;
    }

    public void distroy(Brick brick) {
        this.bricks.remove(brick);

        if (this.bricks.isEmpty()) {
            this.Win = true;
        }
    }

    public void drawLevel(Graphics g) {
        for (Brick brick : this.bricks) {
            brick.drawBrick(g);
        }
    }
}
