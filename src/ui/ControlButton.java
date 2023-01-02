package ui;

import javax.swing.*;
import java.awt.*;

public class ControlButton extends JButton {
    private static final String[] NAMES = new String[]{"SAVE", "QUIT"};

    public ControlButton(int posX, int posY, int width, int height) {
        this.setBounds(
            posX - (width / 2),
            posY,
            width,
            height);
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
