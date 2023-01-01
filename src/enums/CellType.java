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
        new Image().getBufferedImage("res/gameTools/EmptyCell.png"),
        false
    ),

    RESIDENTIAL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/zones/Residential.png"),
            new Image().getImageIcon(
                "res/zones/ResidentialSelected.png")
        },
        new Image().getBufferedImage("res/zones/Residential.png"),
        false
    ),

    COMMERCIAL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/zones/Commercial.png"),
            new Image().getImageIcon(
                "res/zones/CommercialSelected.png")
        },
        new Image().getBufferedImage("res/zones/Commercial.png"),
        false
    ),

    INDUSTRIAL(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/zones/Industrial.png"),
            new Image().getImageIcon(
                "res/zones/IndustrialSelected.png")
        },
        new Image().getBufferedImage("res/zones/Industrial.png"),
        false
    ),

    CROSSROAD(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/roads/Crossroad.png"),
            new Image().getImageIcon(
                "res/roads/CrossroadSelected.png")
        },
        new Image().getBufferedImage("res/roads/Crossroad.png"),
        false
    ),

    VERTICAL_ROAD(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/roads/Vertical.png"),
            new Image().getImageIcon(
                "res/roads/VerticalSelected.png")
        },
        new Image().getBufferedImage("res/roads/Vertical.png"),
        false
    ),

    HORIZONTAL_ROAD(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/roads/Horizontal.png"),
            new Image().getImageIcon(
                "res/roads/HorizontalSelected.png")
        },
        new Image().getBufferedImage("res/roads/Horizontal.png"),
        false
    ),

    CROSS_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/water/CrossPipe.png"),
            new Image().getImageIcon(
                "res/water/CrossPipeSelected.png")
        },
        new Image().getBufferedImage("res/water/CrossPipe.png"),
        false
    ),

    VERTICAL_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/water/VerticalPipe.png"),
            new Image().getImageIcon(
                "res/water/VerticalPipeSelected.png")
        },
        new Image().getBufferedImage("res/water/VerticalPipe.png"),
        false
    ),

    HORIZONTAL_PIPE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/water/HorizontalPipe.png"),
            new Image().getImageIcon(
                "res/water/HorizontalPipeSelected.png")
        },
        new Image().getBufferedImage("res/water/HorizontalPipe.png"),
        false
    ),

    CROSS_CABLE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/power/CrossCable.png"),
            new Image().getImageIcon(
                "res/power/CrossCableSelected.png")
        },
        new Image().getBufferedImage("res/power/CrossCable.png"),
        false
    ),

    VERTICAL_CABLE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/power/VerticalCable.png"),
            new Image().getImageIcon(
                "res/power/VerticalCableSelected.png")
        },
        new Image().getBufferedImage("res/power/VerticalCable.png"),
        false
    ),

    HORIZONTAL_CABLE(
        new ImageIcon[]{
            new Image().getImageIcon(
                "res/power/HorizontalCable.png"),
            new Image().getImageIcon(
                "res/power/HorizontalCableSelected.png")
        },
        new Image().getBufferedImage("res/power/HorizontalCable.png"),
        false
    );

    private final ImageIcon[] imageIcons;
    private final BufferedImage bufferedImage;
    private boolean connected;

    CellType(ImageIcon[] imageIcons, BufferedImage bufferedImage, boolean connected) {
        this.imageIcons = imageIcons;
        this.bufferedImage = bufferedImage;
        this.connected = connected;
    }

    public ImageIcon[] getImageIcons() {
        return this.imageIcons;
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void setConnected(boolean value) {
        this.connected = value;
    }
}
