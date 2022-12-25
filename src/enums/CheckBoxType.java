package enums;

public enum CheckBoxType {
    ZONES(
        new CellType[]{
            CellType.RESIDENTIAL,
            CellType.COMMERCIAL,
            CellType.INDUSTRIAL
        }),
    ROAD(
        new CellType[]{
            CellType.CROSSROAD,
            CellType.VERTICAL_ROAD,
            CellType.HORIZONTAL_ROAD
        }),
    RESOURCES(
        new CellType[]{
            CellType.POWER_LINE,
            CellType.CROSS_PIPE,
            CellType.VERTICAL_PIPE,
            CellType.HORIZONTAL_PIPE
        });

    private final CellType[] cellTypes;

    CheckBoxType(CellType[] cellTypes) {
        this.cellTypes = cellTypes;
    }

    public CellType[] getCellTypes() {
        return this.cellTypes;
    }
}
