package tools;


public enum CellType {
    EMPTY_CELL(
            new String[]{
                    "res/Nove/EmptyCell.png",
                    "res/Nove/Bulldozer.png",
                    "res/Nove/SelectedBulldozer.png"
            },
            "Clear"),
    // Infra
    POWER_LINE(
            new String[]{
                    "res/Nove/PowerLine.png",
                    "res/Nove/SelectedPowerLine.png"
            },
            "Power line"),
    ROAD(
            new String[]{
                    "res/Nove/Road.png",
                    "res/Nove/SelectedRoad.png"
            },
            "Road"),
    PIPE(
            new String[]{
                    "res/Nove/Pipe.png",
                    "res/Nove/SelectedPipe.png"
            },
            "Water pipe"),
    // Zones
    COMMERCIAL(
            new String[]{
                    "res/Nove/EmptyCell.png",
                    "res/Nove/SelectedBulldozer.png"
            },
            "Commercial"),
    INDUSTRIAL(
            new String[]{
                    "res/Nove/EmptyCell.png",
                    "res/Nove/SelectedBulldozer.png"
            },
            "Industrial"),
    RESIDENTIAL(
            new String[]{
                    "res/Nove/EmptyCell.png",
                    "res/Nove/SelectedBulldozer.png"
            },
            "Residential");

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
