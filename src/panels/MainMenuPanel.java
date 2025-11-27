package panels;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JButton;

public class MainMenuPanel extends JPanel {

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGTH = 600;
    private GameController controller;

    private JButton startButton;
    private JButton quitButton;
    private Font btnFont;
    private GridBagConstraints gbc;

    public MainMenuPanel(GameController controller) {

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGTH));
        setBackground(Color.blue);
        setFocusable(true);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        this.controller = controller;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // button top and bottom margin

        btnFont = new Font("SansSerif", Font.BOLD, 20);

        startButton = new JButton("Start Game");
        startButton.setFont(btnFont);
        startButton.setFocusable(false);
        startButton.addActionListener(e -> controller.switchStates(State.GAME));

        quitButton = new JButton("Quit");
        quitButton.setFont(btnFont);
        quitButton.setFocusable(false);
        quitButton.addActionListener(e -> System.exit(0));

        add(startButton, gbc);
        gbc.gridy++;

        add(quitButton, gbc);

    }

}
