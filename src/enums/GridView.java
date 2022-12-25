package enums;

import tools.Image;

import javax.swing.*;

/**
 * Enum uchovava typ bunky, cesty k obrazkom a nazov bunky.
 */
public enum GridView {
    UNDERGROUND(
        new Image().getImageIcon("res/gameTools/Underground.png", 75),
        "Underground"
    ),
    OVERGROUND(
        new Image().getImageIcon("res/gameTools/Overground.png", 75),
        "Overground"
    );

    private final ImageIcon imageIcon;
    private final String stringValue;

    GridView(ImageIcon imageIcon, String stringValue) {
        this.imageIcon = imageIcon;
        this.stringValue = stringValue;
    }

    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

    public String getStringValue() {
        return this.stringValue;
    }

}
