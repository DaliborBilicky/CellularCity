package enums;

import tools.Image;

import javax.swing.*;

/**
 * Enum uchovava typ bunky, cesty k obrazkom a nazov bunky.
 */
public enum GridView {
    UNDERGROUND(
        new Image().getImageIcon("res/gameTools/Underground.png", 110)
    ),
    OVERGROUND(
        new Image().getImageIcon("res/gameTools/Overground.png", 110)
    );

    private final ImageIcon imageIcon;

    GridView(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

}
