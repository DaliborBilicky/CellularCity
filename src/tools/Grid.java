package tools;

import enums.CellType;
import enums.Warning;

import java.util.ArrayList;

/**
 * Trieda vyraba 2D array podla volby. Oddelil som tuto funkcionalitu kvoli
 * citatelnosti.
 */
public class Grid {
    private final Save save;
    private CellType[][] undergroundGrid;
    private CellType[][] overgroundGrid;
    private Warning[][] zonesConnectedToRoad;
    private boolean[][] cableConnection;

    /**
     * Konstruktor inicializuje 2D array a potom pomocou for cyklu nastavi na
     * vsetky pozicie prazdnu bunku.
     */
    public Grid(Save save) {
        this.save = save;
    }

    public void setZoneConnectionToRoad(int i, int j, Warning value) {
        this.zonesConnectedToRoad[i][j] = value;
    }

    public Warning[][] getZonesConnectionToRoad() {
        return this.zonesConnectedToRoad;
    }

    public void setCableConnectionValue(int i, int j, boolean value) {
        this.cableConnection[i][j] = value;
    }

    public boolean isCableConnected(int i, int j) {
        return this.cableConnection[i][j];
    }


    public void setOvergroundGridCell(int i, int j, CellType cellType) {
        this.overgroundGrid[i][j] = cellType;
    }

    public CellType[][] getOvergroundGrid() {
        return this.overgroundGrid;
    }

    public void setUndergroundGridCell(int i, int j, CellType cellType) {
        this.undergroundGrid[i][j] = cellType;
    }

    public CellType[][] getUndergroundGrid() {
        return this.undergroundGrid;
    }

    /**
     * Metoda uklada rozohranu hru.
     */
    public void saveGrids() {
        this.save.saveGame("save/saveOverground.txt", this.overgroundGrid);
        this.save.saveGame("save/saveUnderground.txt", this.undergroundGrid);
    }

    /**
     * @param gamePanelWidth sirka hracieho platna
     * @param panelHeight    vyska platna
     * @param cellSize       velkost bunky
     */
    public void setGrids(int gamePanelWidth, int panelHeight, int cellSize) {
        int m = panelHeight / cellSize;
        int n = gamePanelWidth / cellSize;
        this.undergroundGrid = new CellType[m][n];
        this.overgroundGrid = new CellType[m][n];
        this.cableConnection = new boolean[m][n];
        this.zonesConnectedToRoad = new Warning[m][n];
        this.cableConnection[2][2] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.overgroundGrid[i][j] = CellType.EMPTY_CELL;
                this.undergroundGrid[i][j] = CellType.EMPTY_CELL;
                this.zonesConnectedToRoad[i][j] = Warning.EMPTY;
            }
        }
        this.setSavedGrid();
    }

    /**
     * Metoda cita arraylist a prideluje zapisane hodnoty do mriezky.
     */
    private void setSavedGrid() {
        ArrayList<String[]> fileOverground = save.loadGame(
            "save/saveOverground.txt");
        ArrayList<String[]> fileUnderground = save.loadGame(
            "save/saveUnderground.txt");

        for (int i = 0; i < this.overgroundGrid.length; i++) {
            for (int j = 0; j < this.overgroundGrid[i].length; j++) {
                if (!fileOverground.isEmpty()) {
                    this.setLoadedCell(
                        fileOverground, this.overgroundGrid, i, j);
                }
                if (!fileUnderground.isEmpty()) {
                    this.setLoadedCell(
                        fileUnderground, this.undergroundGrid, i, j);
                }
            }
        }
    }

    /**
     * Metoda prechadza cez ArrayList a ak sa i/j v subore rovna tomu v gride
     * prida ho do gridu.
     *
     * @param file ArrayList riadkov suboru
     * @param grid 2d array
     * @param i    i-ty riadok
     * @param j    j-ty stlpec
     */
    private void setLoadedCell(
        ArrayList<String[]> file, CellType[][] grid, int i, int j) {
        for (String[] line : file) {
            if (Integer.parseInt(line[1]) == i
                && Integer.parseInt(line[2]) == j) {
                grid[i][j] = CellType.valueOf(line[0]);
            }
        }
    }
}
