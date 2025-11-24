package entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements Entity{

    private int UNIT_SIZE;
    private int[] x;
    private int[] y;
    private Point head;
    private List<Point> bodyCoords;
    private int listSize;
    private int bodyParts = 6;
    private int applesEaten = 0;
    private char direction = 'R';
    private int moveCounter = 0;
    private final int MOVE_DELAY = 6;

    public Snake(int UNIT_SIZE, int GAME_UNITS) {

        this.UNIT_SIZE = UNIT_SIZE;
        this.x = new int[GAME_UNITS];
        this.y = new int[GAME_UNITS];
        this.head = new Point(x[0], y[0]);
        this.bodyCoords = new ArrayList<>();

        for(int i = 1; i < bodyParts; i++) {
            bodyCoords.add(new Point(x[i], y[i]));
        }

        listSize = bodyCoords.size();

    }

    public List getBodyCoords() {
        return bodyCoords;
    }

    private void updateBodyCoords() {

        if(listSize != (bodyParts - 1)) {
            bodyCoords.add(new Point(x[bodyParts - 1], y[bodyParts - 1]));
        }

        for(int i = 1; i < bodyParts; i++) {

            bodyCoords.set(i - 1, new Point(x[i], y[i]));

        }

    }

    public void increment() {
        this.bodyParts++;
        this.applesEaten++;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return this.direction;
    }

    public Point getHeadCoords() {
        return this.head;
    }

    private void movement() {

        for(int i = bodyParts - 1; i > 0; i--) {

            x[i] = x[i - 1];
            y[i] = y[i -1];

        }

        switch(direction) {

            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;

        }

        head.setLocation(x[0], y[0]);
        updateBodyCoords();

    }

    @Override
    public void update() {

        moveCounter++;

        if(moveCounter >= MOVE_DELAY) {

            movement();
            moveCounter = 0;

        }

    }

    @Override
    public void draw(Graphics2D g2) {

        for(int i = 0; i < bodyParts; i++) {
            g2.setColor(Color.green);
            g2.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }

    }

}
