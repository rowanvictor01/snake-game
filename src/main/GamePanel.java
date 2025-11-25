package main;

import entities.Apple;
import entities.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    // Dimensions
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;

    private static boolean running = true;

    private static int moveCounter = 0;
    private static final int MOVE_DELAY = 6;

    // Game Entities
    private static Snake snake;
    private static Apple apple;
    private static Random random;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new KeyHandler());

        random = new Random();
        snake = new Snake(UNIT_SIZE, GAME_UNITS);
        Point newAppleCoords = newApple();
        apple = new Apple(newAppleCoords.x, newAppleCoords.y, UNIT_SIZE);

    }

    private Point newApple() {

        // make sure new coords != to snake's coords
        int appleX;
        int appleY;
        boolean isInvalid = false;
        Point appleCoords = new Point();

        do {

            isInvalid = false;

            appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
            appleCoords.setLocation(appleX, appleY);

            for(int i = 0; i < snake.getBodyCoords().size() - 1; i++) {

                if(appleCoords.equals(snake.getBodyCoords().get(i)) || appleCoords.equals(snake.getHeadCoords())) {
                    isInvalid = true;
                }

            }

        } while(isInvalid);

        return appleCoords;

    }
    
    private void checkCollisions() {

        // Snake and Apple Collision
        if(snake.getHeadCoords().equals(apple.getCoords())) {
            snake.increment();
            apple.setCoords(newApple());
        }

        // Snake Self and Border Collisions
        if(snake.checkSelfCollision() || snake.getHeadCoords().x < 0 || snake.getHeadCoords().x > SCREEN_WIDTH || snake.getHeadCoords().y < 0 || snake.getHeadCoords().y > SCREEN_HEIGHT) {
            running = false;
        }

    }

    public void update() {

        moveCounter++;
        if(moveCounter >= MOVE_DELAY) {
            snake.update();
            checkCollisions();
            moveCounter = 0;
        }

        // Call paintComponent After Updating
        repaint();
        Toolkit.getDefaultToolkit().sync();

    }

    @Override
    protected void paintComponent(Graphics g) {

        // Preparation
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        // Draw Entities
        apple.draw(g2);
        snake.draw(g2);

    }

    // Game Loop
    @Override
    public void run() {

        // Time it takes to display each frame
        final long TARGET_FRAME_TIME = 1_000_000_000L / 60;

        long startTime = System.nanoTime();

        while(running) {

            long currentTime = System.nanoTime();
            long elapsedTimeNs = currentTime - startTime;

            if(elapsedTimeNs < TARGET_FRAME_TIME) {

                long sleepTime = (TARGET_FRAME_TIME - elapsedTimeNs) / 1_000_000;

                try {
                    Thread.sleep(sleepTime);
                } catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

            }

            update();
            startTime = System.nanoTime();

        }

    }

    public class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            // super.keyPressed(e);

            switch(e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    if(snake.getDirection() != 'D') {
                        snake.setDirection('U');
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if(snake.getDirection() != 'U') {
                        snake.setDirection('D');
                    }
                    break;

                case KeyEvent.VK_LEFT:
                    if(snake.getDirection() != 'R') {
                        snake.setDirection('L');
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if(snake.getDirection() != 'L') {
                        snake.setDirection('R');
                    }
                    break;

            }

        }

    }

}
