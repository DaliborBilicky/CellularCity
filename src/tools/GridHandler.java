package tools;

import enums.CellType;
import enums.Warning;

import java.util.ArrayList;

/**
 * Trieda vyraba 2D array podla volby. Oddelil som tuto funkcionalitu kvoli
 * citatelnosti.
 */
public class GridHandler {
    private static final String OVERGROUND_FILE = "save/saveOverground.txt";
    private static final String UNDERGROUND_FILE = "save/saveUnderground.txt";
    private final Saver saver;
    private CellType[][] undergroundGrid;
    private CellType[][] overgroundGrid;
    private Warning[][] zonesConnection;

    /**
     * @param saver trieda na ukladanie hry.
     */
    public GridHandler(Saver saver) {
        this.saver = saver;
    }

    /**
     * @param i     i-ty riadok
     * @param j     j-ty stlpec
     * @param value hodnotu ktoru nastavujem
     */
    public void setZoneConnectionWarning(int i, int j, Warning value) {
        this.zonesConnection[i][j] = value;
    }

    /**
     * @return array upozorneni
     */
    public Warning[][] getZonesConnection() {
        return this.zonesConnection;
    }

    /**
     * @param i        i-ty riadok
     * @param j        j-ty stlpec
     * @param cellType hodnotu ktoru nastavujem
     */
    public void setOvergroundGridCell(int i, int j, CellType cellType) {
        this.overgroundGrid[i][j] = cellType;
    }

    /**
     * @return array buniek
     */
    public CellType[][] getOvergroundGrid() {
        return this.overgroundGrid;
    }

    /**
     * @param i        i-ty riadok
     * @param j        j-ty stlpec
     * @param cellType hodnotu ktoru nastavujem
     */
    public void setUndergroundGridCell(int i, int j, CellType cellType) {
        this.undergroundGrid[i][j] = cellType;
    }

    /**
     * @return array buniek
     */
    public CellType[][] getUndergroundGrid() {
        return this.undergroundGrid;
    }

    /**
     * Metoda uklada rozohranu hru.
     */
    public void saveGrids() {
        this.saver.saveGame(OVERGROUND_FILE, this.overgroundGrid);
        this.saver.saveGame(UNDERGROUND_FILE, this.undergroundGrid);
    }

    /**
     * Metoda inicializuje 2D array a potom pomocou for cyklu nastavi na
     * vsetky pozicie prazdnu bunku.
     *
     * @param gamePanelWidth sirka hracieho platna
     * @param panelHeight    vyska platna
     * @param cellSize       velkost bunky
     */
    public void setGrids(int gamePanelWidth, int panelHeight, int cellSize) {
        int m = panelHeight / cellSize;
        int n = gamePanelWidth / cellSize;
        this.undergroundGrid = new CellType[m][n];
        this.overgroundGrid = new CellType[m][n];
        this.zonesConnection = new Warning[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.overgroundGrid[i][j] = CellType.EMPTY_CELL;
                this.undergroundGrid[i][j] = CellType.EMPTY_CELL;
                this.zonesConnection[i][j] = Warning.EMPTY;
            }
        }
        this.setSavedGrid();
    }

    /**
     * Metoda cita arraylist a prideluje zapisane hodnoty do mriezky.
     */
    private void setSavedGrid() {
        ArrayList<String[]> fileOverground = this.saver.loadGame(
            OVERGROUND_FILE);
        ArrayList<String[]> fileUnderground = this.saver.loadGame(
            UNDERGROUND_FILE);

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
