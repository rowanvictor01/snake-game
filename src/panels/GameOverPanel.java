package panels;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GameOverPanel extends JPanel {

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGTH = 600;
    private GameController controller;

    private JButton playAgainBtn;
    private JButton quitBtn;
    private Font btnFont;
    private GridBagConstraints gbc;

    private JLabel titleLabel;
    private JLabel scoreLabel;
    private int finalScore;

    public GameOverPanel(GameController controller) {

        // Setup Panel
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGTH));
        this.setBackground(Color.black);
        this.setFocusable(true);

        // State Manager
        this.controller = controller;

        // Setup layout inside panel
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        // Coordinates
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        btnFont = new Font("SansSerif", Font.BOLD, 20);

        // Labels
        titleLabel = new JLabel("GAME OVER!");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        add(titleLabel, gbc);
        gbc.gridy += 2;

        scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(scoreLabel, gbc);

        gbc.gridy += 6;

        // Play Again Button
        playAgainBtn = new JButton("Play Again");
        playAgainBtn.setFont(btnFont);
        playAgainBtn.setFocusable(false);
        playAgainBtn.addActionListener(e -> controller.switchStates(State.GAME));

        // Quit Button
        quitBtn = new JButton("Quit");
        quitBtn.setFont(btnFont);
        quitBtn.setFocusable(false);
        quitBtn.addActionListener(e -> System.exit(0));

        add(playAgainBtn, gbc);
        gbc.gridy++;
        add(quitBtn, gbc);

    }

    public void updateScoreLabel() {
        scoreLabel.setText("Score: " + finalScore);
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

}
