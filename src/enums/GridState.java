package enums;

import tools.Image;

import javax.swing.*;

/**
 * Enum uchovava typ bunky, cesty k obrazkom a nazov bunky.
 */
public enum GridState {
    OVERGROUND(
        new Image().getImageIcon("res/gameTools/Overground.png", 110),
        true
    ),
    UNDERGROUND(
        new Image().getImageIcon("res/gameTools/Underground.png", 110),
        false
    );

    private final ImageIcon imageIcon;
    private boolean active;

    GridState(ImageIcon imageIcon, boolean active) {
        this.imageIcon = imageIcon;
        this.active = active;
    }

    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean value) {
        this.active = value;
    }
}
