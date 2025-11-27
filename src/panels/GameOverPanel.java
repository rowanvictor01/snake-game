package panels;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class GameOverPanel extends JPanel {

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGTH = 600;

    public GameOverPanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGTH));
        this.setBackground(Color.red);
        this.setFocusable(true);

    }

}
