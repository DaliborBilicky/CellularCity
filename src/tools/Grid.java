package tools;

import enums.CellType;

import java.util.ArrayList;

/**
 * Trieda vyraba 2D array podla volby. Oddelil som tuto funkcionalitu kvoli
 * citatelnosti.
 */
public class Grid {
    private final int panelHeight;
    private final int gamePanelWidth;
    private final int cellSize;
    private final CellType[][] undergroundGrid;
    private final CellType[][] overgroundGrid;

    /**
     * Konstruktor inicializuje 2D array a potom pomocou for cyklu nastavi na
     * vsetky pozicie prazdnu bunku.
     */
    public Grid(int panelHeight, int gamePanelWidth, int cellSize, Save save) {
        this.panelHeight = panelHeight;
        this.gamePanelWidth = gamePanelWidth;
        this.cellSize = cellSize;
        this.undergroundGrid =
            new CellType
                [this.panelHeight / this.cellSize]
                [this.gamePanelWidth / this.cellSize];
        this.overgroundGrid =
            new CellType
                [this.panelHeight / this.cellSize]
                [this.gamePanelWidth / this.cellSize];
        ArrayList<String[]> fileOverground = save.loadGame(
            "saves/saveOverground.txt");
        ArrayList<String[]> fileUnderground = save.loadGame(
            "saves/saveUnderground.txt");

        this.setGrid();
        if (!fileOverground.isEmpty()) {
            this.setLoadedGrid(fileOverground, this.overgroundGrid);
        }
        if (!fileUnderground.isEmpty()) {
            this.setLoadedGrid(fileUnderground, this.undergroundGrid);
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

    private void setGrid() {
        for (int i = 0; i < this.panelHeight / this.cellSize; i++) {
            for (int j = 0; j < this.gamePanelWidth / this.cellSize; j++) {
                this.overgroundGrid[i][j] = CellType.EMPTY_CELL;
                this.undergroundGrid[i][j] = CellType.EMPTY_CELL;
            }
        }
    }

    private void setLoadedGrid(ArrayList<String[]> file, CellType[][] grid) {
        for (int i = 0; i < this.panelHeight / this.cellSize; i++) {
            for (int j = 0; j < this.gamePanelWidth / this.cellSize; j++) {
                for (String[] line : file) {
                    if (Integer.parseInt(line[1]) == i
                        && Integer.parseInt(line[2]) == j) {
                        grid[i][j] = CellType.valueOf(line[0]);
                    }
                }
            }
        }
    }
}
