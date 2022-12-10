package tools;

import java.awt.image.BufferedImage;

/**
 * Enum uchovava typ bunky, cesty k obrazkom a nazov bunky.
 */
public enum CellType {
    EMPTY_CELL(
        new String[]{
            "res/Bulldozer.png",
            "res/BulldozerSelected.png"
        },
        new Image().getMyImage("res/EmptyCell.png"),
        "Clear"),

    RESIDENTIAL(
        new String[]{
            "res/Residential.png",
            "res/ResidentialSelected.png"
        },
        new Image().getMyImage("res/Residential.png"),
        "Residential"),

    COMMERCIAL(
        new String[]{
            "res/Commercial.png",
            "res/CommercialSelected.png"
        },
        new Image().getMyImage("res/Commercial.png"),
        "Commercial"),

    INDUSTRIAL(
        new String[]{
            "res/Industrial.png",
            "res/IndustrialSelected.png"
        },
        new Image().getMyImage("res/Industrial.png"),
        "Industrial"),

    POWER_LINE(
        new String[]{
            "res/PowerLine.png",
            "res/PowerLineSelected.png"
        },
        new Image().getMyImage("res/PowerLine.png"),
        "Power line"),

    PIPE(
        new String[]{
            "res/Pipe.png",
            "res/PipeSelected.png"
        },
        new Image().getMyImage("res/Pipe.png"),
        "Water pipe"),

    ROAD(
        new String[]{
            "res/Road.png",
            "res/RoadSelected.png"
        },
        new Image().getMyImage("res/Road.png"),
        "Road");

    /*
     * todo
     *   NO_WATER(new String[]{
     *  "src/NoWater.png"
     *   },
     *       "No water"),
     *   NO_POWER(new String[]{
     *       "res/NoPower.png"
     *   },
     *       "No power"),
     *   NO_ROAD(new String[]{
     *       "res/NoPower.png"
     *   },
     *       "No road");
     */

    private final String[] imagePaths;
    private final BufferedImage image;
    private final String cellName;

    CellType(String[] imagePaths, BufferedImage image, String cellName) {
        this.imagePaths = imagePaths;
        this.image = image;
        this.cellName = cellName;
    }

    public String[] getImagePaths() {
        return this.imagePaths;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public String getCellName() {
        return this.cellName;
    }

}
