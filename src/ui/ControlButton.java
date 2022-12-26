package ui;

import javax.swing.*;
import java.awt.*;

public class ControlButton extends JButton {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;

    public ControlButton(int posX, int posY, String text) {
        this.setBounds(
            posX - (BUTTON_WIDTH / 2),
            posY - (BUTTON_HEIGHT / 2),
            BUTTON_WIDTH,
            BUTTON_HEIGHT);
        this.setFocusable(false);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorderPainted(true);
        this.setFont(new Font("Arial", Font.PLAIN, 16));
        this.setText(text);
    }
}
