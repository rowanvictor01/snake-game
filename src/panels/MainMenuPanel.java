package panels;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JButton;

public class MainMenuPanel extends JPanel {

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGTH = 600;
    private GameController controller;

    private JButton startBtn;
    private JButton quitBtn;
    private Font btnFont;
    private GridBagConstraints gbc;

    public MainMenuPanel(GameController controller) {

        // Setup Panel
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGTH));
        setBackground(Color.black);
        setFocusable(true);

        // State Manager
        this.controller = controller;

        // Setup Layout inside Panel
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        // Btn Coordinates
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // button top and bottom margin

        btnFont = new Font("SansSerif", Font.BOLD, 20);

        // Start Button
        startBtn = new JButton("Start Game");
        startBtn.setFont(btnFont);
        startBtn.setFocusable(false);
        startBtn.addActionListener(e -> controller.switchStates(State.GAME));

        // Quit Button
        quitBtn = new JButton("Quit");
        quitBtn.setFont(btnFont);
        quitBtn.setFocusable(false);
        quitBtn.addActionListener(e -> System.exit(0));

        // Add Btns to Panel
        add(startBtn, gbc);
        gbc.gridy++;
        add(quitBtn, gbc);

    }

}
