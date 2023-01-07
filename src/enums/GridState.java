package enums;

import tools.Image;

import javax.swing.ImageIcon;

/**
 * Enum uchovava stavy hry. Kazdy stav obsahuje ikonu pre check box a boolean
 * ci je stav aktivny alebo nie.
 */
public enum GridState {
    OVERGROUND(
        new Image().getImageIcon("res/tools/Overground.png"),
        true
    ),
    UNDERGROUND(
        new Image().getImageIcon("res/tools/Underground.png"),
        false
    );

    private final ImageIcon imageIcon;
    private boolean active;

    GridState(ImageIcon imageIcon, boolean active) {
        this.imageIcon = imageIcon;
        this.active = active;
    }

    /**
     * @return ikonu pre check box
     */
    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

    /**
     * @return ci je stav aktivny
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * @param value nastavi ci je stav aktivny
     */
    public void setActive(boolean value) {
        this.active = value;
    }

}
