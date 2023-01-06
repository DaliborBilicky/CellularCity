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
    RESOURCES(
        new CellType[]{
            CellType.WATER,
            CellType.POWER
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
