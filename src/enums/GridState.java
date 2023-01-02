package enums;

import tools.Image;

import javax.swing.ImageIcon;

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
