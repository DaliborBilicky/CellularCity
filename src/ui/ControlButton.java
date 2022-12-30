package ui;

import javax.swing.*;
import java.awt.*;

public class ControlButton extends JButton {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final String[] NAMES = new String[]{"SAVE", "QUIT"};

    public ControlButton(int posX, int posY) {
        this.setBounds(
            posX - (BUTTON_WIDTH / 2),
            posY - (BUTTON_HEIGHT / 2),
            BUTTON_WIDTH,
            BUTTON_HEIGHT);
        this.setFocusable(false);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorderPainted(true);
        this.setFont(new Font("Arial", Font.BOLD, 32));
    }

    public void setName(int num) {
        this.setText(NAMES[num]);
    }
}
