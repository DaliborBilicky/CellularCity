package enums;

import tools.Image;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;


/**
 * Enum uchovava typy buniek a kazdy typ odahuje ikonu alebo obrazok pre
 * dalsie puzitie.
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

    CROSS_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/pipe/Cross.png"),
            new Image().getImageIcon(
                "res/pipe/CrossSelected.png")
        },
        new Image().getBufferedImage("res/pipe/Cross.png")
    ),

    VERTICAL_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/pipe/Vertical.png"),
            new Image().getImageIcon(
                "res/pipe/VerticalSelected.png")
        },
        new Image().getBufferedImage("res/pipe/Vertical.png")
    ),

    HORIZONTAL_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/pipe/Horizontal.png"),
            new Image().getImageIcon(
                "res/pipe/HorizontalSelected.png")
        },
        new Image().getBufferedImage("res/pipe/Horizontal.png")
    ),

    CROSS_CABLE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/cable/Cross.png"),
            new Image().getImageIcon(
                "res/cable/CrossSelected.png")
        },
        new Image().getBufferedImage("res/cable/Cross.png")
    ),

    VERTICAL_CABLE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/cable/Vertical.png"),
            new Image().getImageIcon(
                "res/cable/VerticalSelected.png")
        },
        new Image().getBufferedImage("res/cable/Vertical.png")
    ),

    HORIZONTAL_CABLE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/cable/Horizontal.png"),
            new Image().getImageIcon(
                "res/cable/HorizontalSelected.png")
        },
        new Image().getBufferedImage("res/cable/Horizontal.png")
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
