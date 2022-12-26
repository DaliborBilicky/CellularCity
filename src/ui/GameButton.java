package ui;

import tools.Image;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {

    private static final int BUTTON_WIDTH = 125;
    private static final int BUTTON_HEIGHT = 125;
    private int counterLimit;
    private int counter;

    public GameButton(int posX, int posY) {
        this.setBounds(
            posX - (BUTTON_WIDTH / 2),
            posY - (BUTTON_HEIGHT / 2),
            BUTTON_WIDTH,
            BUTTON_HEIGHT);
        this.setFocusable(false);
        this.setBackground(Color.LIGHT_GRAY);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorderPainted(true);
        this.setIcon(new Image().getImageIcon(
            "res/gameTools/Button.png", 125));
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounterLimit(int num) {
        this.counterLimit = num - 1;
    }

    public void increaseCounter() {
        if (this.counter < this.counterLimit) {
            this.counter++;
        } else {
            this.counter = 0;
        }
    }
}
