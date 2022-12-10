package tools;

import java.awt.image.BufferedImage;

/**
 * Trieda vyraba 2D array podla volby. Oddelil som tuto funkcionalitu kvoli
 * citatelnosti.
 */
public class Grid {
    private final CellType[][] grid;

    /**
     * Konstruktor inicializuje 2D array a potom pomocou for cyklu nastavi na
     * vsetky pozicie prazdnu bunku.
     */
    public Grid(int panelHeight, int gamePanelWidth, int cellSize) {
        this.grid = new CellType[panelHeight / cellSize][gamePanelWidth / cellSize];

        for (int i = 0; i < panelHeight / cellSize; i++) {
            for (int j = 0; j < gamePanelWidth / cellSize; j++) {
                this.grid[i][j] = CellType.EMPTY_CELL;
            }
        }
    }

    public CellType[][] getGrid() {
        return this.grid;
    }
}
