package panels;

import main.Window;

public class GameController {

    private Window window;
    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;
    private GameOverPanel gameOverPanel;
    private State currentState;

    public GameController() {

        this.window = new Window();
        this.mainMenuPanel = new MainMenuPanel(this);
        this.gamePanel = new GamePanel();
        this.gameOverPanel = new GameOverPanel();

    }

    public void switchStates(State newState) {

        currentState = newState;
        window.getContentPane().removeAll();

        switch(currentState) {

            case State.MAIN_MENU -> window.add(mainMenuPanel);

            case State.GAME -> {
                window.add(gamePanel);
                startGamePanel(gamePanel);
                gamePanel.requestFocusInWindow();
            }

            case State.GAME_OVER -> window.add(gameOverPanel);

        }

        window.pack();
        window.revalidate();
        window.repaint();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    private void startGamePanel(GamePanel gp) {
        Thread gameThread = new Thread(gp);
        gameThread.start();
    }

}
