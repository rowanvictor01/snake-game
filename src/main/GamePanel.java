package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Dimensions
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;

    // Time it takes to display each frame
    private final long TARGET_FRAME_TIME = 1_000_000_000L / 60;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);

    }

    // Game Loop
    @Override
    public void run() {
        // code
    }

}
