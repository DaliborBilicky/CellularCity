package enums;

import tools.Image;

import javax.swing.*;
import java.awt.image.BufferedImage;

public enum CellType {
    EMPTY_CELL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/gameTools/Bulldozer.png", 75),
            new Image().getImageIcon(
                "res/gameTools/BulldozerSelected.png", 75)
        },
        new Image().getBufferedImage("res/gameTools/EmptyCell.png")
    ),

    RESIDENTIAL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/zones/Residential.png", 75),
            new Image().getImageIcon(
                "res/zones/ResidentialSelected.png", 75)
        },
        new Image().getBufferedImage("res/zones/Residential.png")
    ),

    COMMERCIAL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/zones/Commercial.png", 75),
            new Image().getImageIcon(
                "res/zones/CommercialSelected.png", 75)
        },
        new Image().getBufferedImage("res/zones/Commercial.png")
    ),

    INDUSTRIAL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/zones/Industrial.png", 75),
            new Image().getImageIcon(
                "res/zones/IndustrialSelected.png", 75)
        },
        new Image().getBufferedImage("res/zones/Industrial.png")
    ),

    CROSSROAD(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/roads/Crossroad.png", 75),
            new Image().getImageIcon(
                "res/roads/CrossroadSelected.png", 75)
        },
        new Image().getBufferedImage("res/roads/Crossroad.png")
    ),

    VERTICAL_ROAD(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/roads/Vertical.png", 75),
            new Image().getImageIcon(
                "res/roads/VerticalSelected.png", 75)
        },
        new Image().getBufferedImage("res/roads/Vertical.png")
    ),

    HORIZONTAL_ROAD(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/roads/Horizontal.png", 75),
            new Image().getImageIcon(
                "res/roads/HorizontalSelected.png", 75)
        },
        new Image().getBufferedImage("res/roads/Horizontal.png")
    ),

    POWER_LINE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/resources/PowerLine.png", 75),
            new Image().getImageIcon(
                "res/resources/PowerLineSelected.png", 75)
        },
        new Image().getBufferedImage("res/resources/PowerLine.png")
    ),

    CROSS_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/resources/CrossPipe.png", 75),
            new Image().getImageIcon(
                "res/resources/CrossPipeSelected.png", 75)
        },
        new Image().getBufferedImage("res/resources/CrossPipe.png")
    ),

    VERTICAL_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/resources/VerticalPipe.png", 75),
            new Image().getImageIcon(
                "res/resources/VerticalPipeSelected.png", 75)
        },
        new Image().getBufferedImage("res/resources/VerticalPipe.png")
    ),

    HORIZONTAL_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/resources/HorizontalPipe.png", 75),
            new Image().getImageIcon(
                "res/resources/HorizontalPipeSelected.png", 75)
        },
        new Image().getBufferedImage("res/resources/HorizontalPipe.png")
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
