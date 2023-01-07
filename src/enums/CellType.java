package enums;

import tools.Image;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;


/**
 * Enum uchovava typy buniek a kazdy typ odahuje ikonu a obrazok predalsie
 * puzitie.
 */
public enum CellType {
    EMPTY_CELL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/tools/Bulldozer.png"),
            new Image().getImageIcon(
                "res/tools/BulldozerSelected.png")
        },
        new Image().getBufferedImage("res/tools/EmptyCell.png")
    ),

    RESIDENTIAL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/zones/Residential.png"),
            new Image().getImageIcon(
                "res/zones/ResidentialSelected.png")
        },
        new Image().getBufferedImage("res/zones/Residential.png")
    ),

    COMMERCIAL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/zones/Commercial.png"),
            new Image().getImageIcon(
                "res/zones/CommercialSelected.png")
        },
        new Image().getBufferedImage("res/zones/Commercial.png")
    ),

    INDUSTRIAL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/zones/Industrial.png"),
            new Image().getImageIcon(
                "res/zones/IndustrialSelected.png")
        },
        new Image().getBufferedImage("res/zones/Industrial.png")
    ),

    CROSSROAD(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/roads/Cross.png"),
            new Image().getImageIcon(
                "res/roads/CrossSelected.png")
        },
        new Image().getBufferedImage("res/roads/Cross.png")
    ),

    VERTICAL_ROAD(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/roads/Vertical.png"),
            new Image().getImageIcon(
                "res/roads/VerticalSelected.png")
        },
        new Image().getBufferedImage("res/roads/Vertical.png")
    ),

    HORIZONTAL_ROAD(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/roads/Horizontal.png"),
            new Image().getImageIcon(
                "res/roads/HorizontalSelected.png")
        },
        new Image().getBufferedImage("res/roads/Horizontal.png")
    ),

    WATER(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/resources/Water.png"),
            new Image().getImageIcon(
                "res/resources/WaterSelected.png")
        },
        new Image().getBufferedImage("res/resources/Water.png")
    ),

    POWER(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/resources/Power.png"),
            new Image().getImageIcon(
                "res/resources/PowerSelected.png")
        },
        new Image().getBufferedImage("res/resources/Power.png")
    );

    private final ImageIcon[] imageIcons;
    private final BufferedImage bufferedImage;

    CellType(ImageIcon[] imageIcons, BufferedImage bufferedImage) {
        this.imageIcons = imageIcons;
        this.bufferedImage = bufferedImage;
    }

    public ImageIcon[] getImageIcons() {
        return this.imageIcons;
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

}
