package entities;

import java.awt.*;

public class Apple implements Entity {

    private int UNIT_SIZE;
    private int x;
    private int y;

    public Apple(int x, int y, int UNIT_SIZE) {

        this.UNIT_SIZE = UNIT_SIZE;
        this.x = x;
        this.y = y;

    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillOval(x, y, UNIT_SIZE, UNIT_SIZE);
    }

}
