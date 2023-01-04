package enums;


/**
 * Enum rozdeluje bunky do podkategorii aby sa lepsie pracovalo s check boxmi.
 */
public enum CheckBoxType {
    ZONE(
        new CellType[]{
            CellType.RESIDENTIAL,
            CellType.COMMERCIAL,
            CellType.INDUSTRIAL
        }
    ),
    ROAD(
        new CellType[]{
            CellType.CROSSROAD,
            CellType.VERTICAL_ROAD,
            CellType.HORIZONTAL_ROAD
        }
    ),
    PIPE(
        new CellType[]{
            CellType.CROSS_PIPE,
            CellType.VERTICAL_PIPE,
            CellType.HORIZONTAL_PIPE
        }
    ),
    CABLE(
        new CellType[]{
            CellType.CROSS_CABLE,
            CellType.VERTICAL_CABLE,
            CellType.HORIZONTAL_CABLE
        }
    ),
    EMPTY_CELL(
        new CellType[]{
            CellType.EMPTY_CELL
        }
    );

    private final CellType[] cellTypes;


    CheckBoxType(CellType[] cellTypes) {
        this.cellTypes = cellTypes;
    }

    public CellType[] getCellTypes() {
        return this.cellTypes;
    }
}
