package main;

import entities.Snake;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Dimensions
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;

    private boolean running = true;

    // Game Entities
    private static Snake snake;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);

        snake = new Snake(UNIT_SIZE, GAME_UNITS);

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

}
