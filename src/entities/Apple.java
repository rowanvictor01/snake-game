package entities;

import utils.SpriteManager;
import panels.GamePanel;

import java.awt.*;

public class Apple implements Entity {

    private int UNIT_SIZE;
    private int x;
    private int y;

    private GamePanel gp;
    private Image appleSprite;

    public Apple(int x, int y, int UNIT_SIZE, GamePanel gp) {

        this.gp = gp;
        appleSprite = SpriteManager.get("apple");

        this.UNIT_SIZE = UNIT_SIZE;
        this.x = x;
        this.y = y;

    }

    public void setCoords(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point getCoords() {
        return new Point(this.x, this.y);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(appleSprite, x, y, UNIT_SIZE, UNIT_SIZE, gp);
    }

}
