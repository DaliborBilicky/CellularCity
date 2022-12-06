package tools;

/**
 * Enum uchovava typ bunky, cesty k obrazkom a nazov bunky.
 */
public enum CellType {
    EMPTY_CELL(
        new String[]{
            "res/EmptyCell.png",
            "res/Bulldozer.png",
            "res/SelectedBulldozer.png"
        },
        "Clear"),

    RESIDENTIAL(new String[]{
        "res/Residential.png",
        "res/SelectedResidential.png"
    },
        "Residential"),

    COMMERCIAL(new String[]{
        "res/Commercial.png",
        "res/SelectedCommercial.png"
    },
        "Commercial"),

    INDUSTRIAL(new String[]{
        "res/Industrial.png",
        "res/SelectedIndustrial.png"
    },
        "Industrial"),

    PIPE(new String[]{
        "res/Pipe.png",
        "res/SelectedPipe.png"
    },
        "Water pipe"),

    POWER_LINE(new String[]{
        "res/PowerLine.png",
        "res/SelectedPowerLine.png"
    },
        "Power line"),

    ROAD(new String[]{
        "res/Road.png",
        "res/SelectedRoad.png"
    },
        "Road");

    private final String[] imagePaths;
    private final String cellName;

    CellType(String[] imagePaths, String cellName) {
        this.imagePaths = imagePaths;
        this.cellName = cellName;
    }

    public String[] getImagePath() {
        return this.imagePaths;
    }

    public String getCellName() {
        return this.cellName;
    }
}
