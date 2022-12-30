package ui;

import tools.Image;

import javax.swing.*;

public class Satisfaction extends JLabel {
    private static final int WIDTH = 150;
    private static final int HEIGHT = 150;

    public Satisfaction(int posX, int posY) {
        this.setBounds(
            posX - (WIDTH / 2),
            posY - (HEIGHT / 2),
            WIDTH,
            HEIGHT);
        this.setFocusable(false);
        this.setIcon(
            new Image().getImageIcon("res/gameTools/ThumbUp.png", WIDTH)
        );
    }
}
