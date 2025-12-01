package panels;

import main.Window;
import utils.HighScoreManager;

public class GameController {

    private Window window;
    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;
    private GameOverPanel gameOverPanel;
    private State currentState;

    public GameController() {

        this.window = new Window();
        this.mainMenuPanel = new MainMenuPanel(this);
        this.gameOverPanel = new GameOverPanel(this);

    }

    public void switchStates(State newState) {

        currentState = newState;
        window.getContentPane().removeAll();

        switch(currentState) {

            case State.MAIN_MENU -> window.add(mainMenuPanel);

            case State.GAME -> {
                gamePanel = new GamePanel(this);
                window.add(gamePanel);
                gamePanel.startGame();
                gamePanel.requestFocusInWindow();
            }

            case State.GAME_OVER -> {
                gameOverPanel.setFinalScore(gamePanel.finalScore);
                gameOverPanel.updateScoreLabel();
                checkNewHighScore();
                gamePanel.stopGame();
                gamePanel.waitForStop();
                window.add(gameOverPanel);
            }

        }

        window.pack();
        window.revalidate();
        window.repaint();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    private void checkNewHighScore() {
        if(gamePanel.finalScore > HighScoreManager.loadHighScore()) {
            gameOverPanel.updateHighScoreLabel(gamePanel.finalScore);
            HighScoreManager.saveHighScore(gamePanel.finalScore);
        }
    }

}
