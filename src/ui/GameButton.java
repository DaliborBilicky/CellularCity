package ui;

import tools.Image;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {
    private int counterLimit;
    private int counter;

    public GameButton(int posX, int posY, int size) {
        this.setBounds(
            posX - (size / 2),
            posY,
            size,
            size);
        this.setFocusable(false);
        this.setBackground(Color.LIGHT_GRAY);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorderPainted(true);
        this.setIcon(new Image().getImageIcon(
            "res/tools/Button.png", 125));
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
