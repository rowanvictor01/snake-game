package main;

import javax.swing.*;

public class Window extends JFrame {

    private final GamePanel gamePanel;

    public Window() {

        this.setTitle("Snake Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.gamePanel = new GamePanel();
        this.add(gamePanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

}
