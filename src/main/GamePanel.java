package main;

import entities.Apple;
import entities.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    // Dimensions
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;

    private static boolean running = true;

    // Game Entities
    private static Snake snake;
    private static Apple apple;
    private static Random random;
    private static Map<Character, Integer> coordinates = new HashMap<>();

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new KeyHandler());

        random = new Random();
        snake = new Snake(UNIT_SIZE, GAME_UNITS);
        Map<Character, Integer> appleCoords = newApple();
        apple = new Apple(appleCoords.get('X'), appleCoords.get('Y'), UNIT_SIZE);

    }

    private Map newApple() {

        int appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        int appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        coordinates.put('X', appleX);
        coordinates.put('Y', appleY);

        return coordinates;

    }

    public void update() {

        snake.update();

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
