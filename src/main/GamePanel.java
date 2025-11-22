package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Dimensions
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;

    // Time it takes to display each frame
    private final long TARGET_FRAME_TIME = 1_000_000_000L / 60;

    private boolean running = false;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);

    }

    public void update() {
        // code
    }

    @Override
    protected void paintComponent(Graphics g) {
        // code
    }

    // Game Loop
    @Override
    public void run() {

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
