package enums;

public enum CheckBoxType {
    ZONE(
        new CellType[]{
            CellType.RESIDENTIAL,
            CellType.COMMERCIAL,
            CellType.INDUSTRIAL
        },
        "Zone"
    ),
    ROAD(
        new CellType[]{
            CellType.CROSSROAD,
            CellType.VERTICAL_ROAD,
            CellType.HORIZONTAL_ROAD
        },
        "Road"
    ),
    WATER(
        new CellType[]{
            CellType.CROSS_PIPE,
            CellType.VERTICAL_PIPE,
            CellType.HORIZONTAL_PIPE
        },
        "Water"
    ),
    POWER(
        new CellType[]{
            CellType.CROSS_CABLE,
            CellType.VERTICAL_CABLE,
            CellType.HORIZONTAL_CABLE
        },
        "Power"
    ),
    EMPTY_CELL(
        new CellType[]{
            CellType.EMPTY_CELL
        },
        "EmptyCell"
    );

    private final CellType[] cellTypes;

    private final String stringValue;

    CheckBoxType(CellType[] cellTypes, String stringValue) {
        this.cellTypes = cellTypes;
        this.stringValue = stringValue;
    }

    public CellType[] getCellTypes() {
        return this.cellTypes;
    }

    public String getStringValue() {
        return this.stringValue;
    }
}
