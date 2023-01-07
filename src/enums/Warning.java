package enums;

import tools.Image;

import java.awt.image.BufferedImage;

/**
 * Enum uchovava typy upozorneni a kazde upozornenie obsahuje obrazok ktory
 * reprezentuje.
 */
public enum Warning {
    EMPTY(new Image().getBufferedImage("res/tools/EmptyCell.png")),
    NO_ROAD(new Image().getBufferedImage("res/roads/NoRoad.png")),
    NO_WATER(new Image().getBufferedImage("res/resources/NoWater.png")),
    NO_POWER(new Image().getBufferedImage("res/resources/NoPower.png")),
    CONNECTED(new Image().getBufferedImage("res/tools/EmptyCell.png"));

    private final BufferedImage bufferedImage;

    Warning(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    /**
     * @return obrazok pre typ upozornenia
     */
    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

}
