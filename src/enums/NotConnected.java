package enums;

import tools.Image;

import javax.swing.*;
import java.awt.image.BufferedImage;

public enum NotConnected {
    NO_ROAD(
        new Image().getBufferedImage("res/roads/NoRoad.png"),
        "NoRoad"),

    NO_POWER(
        new Image().getBufferedImage("res/resources/NoPower"),
        "NoPower"),

    NO_WATER(
        new Image().getBufferedImage("res/resources/NoWater"),
        "NoWater");

    private final BufferedImage bufferedImage;
    private final String cellName;

    NotConnected(BufferedImage bufferedImage, String cellName) {
        this.bufferedImage = bufferedImage;
        this.cellName = cellName;
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

    public String getCellName() {
        return this.cellName;
    }
}
