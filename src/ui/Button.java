package ui;

import tools.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {

    private static final int BUTTON_WIDTH = 125;
    private static final int BUTTON_HEIGHT = 125;
    private int counter;
    private int tempCounter;

    public Button(int posX, int posY) {
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
            "res/gameTools/ButtonSelected.png", 100));
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter - 1;
        this.tempCounter = counter - 1;
    }

    public void subtractFromCounter() {
        this.counter--;
    }

    public void resetCounter() {
        this.counter = this.tempCounter;
    }
}
