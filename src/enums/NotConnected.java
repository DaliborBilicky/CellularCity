package enums;

import tools.Image;

import java.awt.image.BufferedImage;

public enum NotConnected {
    NO_ROAD(
        new Image().getBufferedImage("res/notConnected/NoRoad.png")),
    NO_WATER(
        new Image().getBufferedImage("res/notConnected/NoWater")),

    NO_POWER(
        new Image().getBufferedImage("res/notConnected/NoPower"));

    private final BufferedImage bufferedImage;

    NotConnected(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

}
