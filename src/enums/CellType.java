package enums;

import tools.Image;

import javax.swing.*;
import java.awt.image.BufferedImage;

public enum CellType {
    EMPTY_CELL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/gameTools/Bulldozer.png"),
            new Image().getImageIcon(
                "res/gameTools/BulldozerSelected.png")
        },
        new Image().getBufferedImage("res/gameTools/EmptyCell.png")
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
                "res/roads/Crossroad.png"),
            new Image().getImageIcon(
                "res/roads/CrossroadSelected.png")
        },
        new Image().getBufferedImage("res/roads/Crossroad.png")
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
                "res/water/CrossPipe.png"),
            new Image().getImageIcon(
                "res/water/CrossPipeSelected.png")
        },
        new Image().getBufferedImage("res/water/CrossPipe.png")
    ),

    VERTICAL_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/water/VerticalPipe.png"),
            new Image().getImageIcon(
                "res/water/VerticalPipeSelected.png")
        },
        new Image().getBufferedImage("res/water/VerticalPipe.png")
    ),

    HORIZONTAL_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/water/HorizontalPipe.png"),
            new Image().getImageIcon(
                "res/water/HorizontalPipeSelected.png")
        },
        new Image().getBufferedImage("res/water/HorizontalPipe.png")
    ),

    CROSS_CABLE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/power/CrossCable.png"),
            new Image().getImageIcon(
                "res/power/CrossCableSelected.png")
        },
        new Image().getBufferedImage("res/power/CrossCable.png")
    ),

    VERTICAL_CABLE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/power/VerticalCable.png"),
            new Image().getImageIcon(
                "res/power/VerticalCableSelected.png")
        },
        new Image().getBufferedImage("res/power/VerticalCable.png")
    ),

    HORIZONTAL_CABLE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/power/HorizontalCable.png"),
            new Image().getImageIcon(
                "res/power/HorizontalCableSelected.png")
        },
        new Image().getBufferedImage("res/power/HorizontalCable.png")
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
