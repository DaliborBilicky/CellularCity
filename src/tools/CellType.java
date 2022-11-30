package tools;


public enum CellType {
    EMPTY_CELL("res/EmptyCell.png", "Clear"),
    // Infra
    POWER_LINE("res/EmptyCell.png", "Power line"),
    ROAD("res/EmptyCell.png", "Road"),
    WATER_PIPE("res/EmptyCell.png", "Water pipe"),
    // Zones
    COMMERCIAL("res/EmptyCell.png", "Commercial"),
    INDUSTRIAL("res/zones/Industrial.png", "Industrial"),
    RESIDENTIAL("res/zones/Residential.png", "Residential");

    private final String imagePath;
    private final String cellName;

    CellType(String imagePath, String cellName) {
        this.imagePath = imagePath;
        this.cellName = cellName;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getCellName() {
        return this.cellName;
    }
}
