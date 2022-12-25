package tools;

import enums.CellType;

/**
 * Trieda vyraba 2D array podla volby. Oddelil som tuto funkcionalitu kvoli
 * citatelnosti.
 */
public class Grid {
    private final CellType[][] undergroundGrid;
    private final CellType[][] overgroundGrid;

    /**
     * Konstruktor inicializuje 2D array a potom pomocou for cyklu nastavi na
     * vsetky pozicie prazdnu bunku.
     */
    public Grid(int panelHeight, int gamePanelWidth, int cellSize) {
        this.undergroundGrid =
            new CellType[panelHeight / cellSize][gamePanelWidth / cellSize];
        this.overgroundGrid =
            new CellType[panelHeight / cellSize][gamePanelWidth / cellSize];

        for (int i = 0; i < panelHeight / cellSize; i++) {
            for (int j = 0; j < gamePanelWidth / cellSize; j++) {
                this.undergroundGrid[i][j] = CellType.EMPTY_CELL;
                this.overgroundGrid[i][j] = CellType.EMPTY_CELL;
            }
        }
    }

    public void setUndergroundGridCell(int i, int j, CellType cellType) {
        this.undergroundGrid[i][j] = cellType;
    }

    public CellType[][] getUndergroundGrid() {
        return this.undergroundGrid;
    }

    public void setOvergroundGridCell(int i, int j, CellType cellType) {
        this.overgroundGrid[i][j] = cellType;
    }

    public CellType[][] getOvergroundGrid() {
        return this.overgroundGrid;
    }
}
