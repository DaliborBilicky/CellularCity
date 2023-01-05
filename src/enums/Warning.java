package enums;

import tools.Image;

import java.awt.image.BufferedImage;

public enum Warning {
    EMPTY(new Image().getBufferedImage("res/tools/EmptyCell.png")),
    NO_ROAD(new Image().getBufferedImage("res/warning/NoRoad.png")),
    NO_WATER(new Image().getBufferedImage("res/warning/NoWater.png")),

    NO_POWER(new Image().getBufferedImage("res/warning/NoPower.png"));

    private final BufferedImage bufferedImage;

    Warning(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

}
