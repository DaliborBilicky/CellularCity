package game;

import enums.CellType;
import enums.CheckBoxType;
import enums.Warning;
import tools.GridHandler;

/**
 * Trieda ktora ma na starosti hlavnu logiku hry. Kontroluje ci su zony
 * pripojene a podla toho nastavi upozornenie.
 */
public class ConnectionChecker {
    private final GridHandler gridHandler;
    private final int m; // pocet riadkov
    private final int n; // pocet stlpcov

    /**
     * Kostruktor pre ConnectionChecker
     *
     * @param gridHandler trieda s nim pracuje cely cas.
     */
    public ConnectionChecker(GridHandler gridHandler) {
        this.gridHandler = gridHandler;
        this.m = this.gridHandler.getOvergroundGrid().length;
        this.n = this.gridHandler.getOvergroundGrid()[0].length;
    }

    /**
     * Metoda iteruje cez grid a kontroluje ci bunka je zona ak ano pomocou
     * switchu kontroluje jej pripojenie.
     */
    public void checkConnection() {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                switch (this.gridHandler.getOvergroundGrid()[i][j]) {
                    case RESIDENTIAL, COMMERCIAL, INDUSTRIAL:
                        switch (this.gridHandler.getZonesConnection()[i][j]) {
                            case EMPTY:
                                this.gridHandler.setZoneConnectionWarning(
                                    i, j, Warning.NO_ROAD);
                                break;
                            case NO_ROAD:
                                this.checkRoadConnection(i, j);
                                break;
                            case NO_WATER:
                                if (this.isConnectedToRoad(i, j)) {
                                    this.checkResConnection(
                                        CellType.WATER, Warning.NO_POWER, i, j);
                                } else {
                                    this.gridHandler.setZoneConnectionWarning(
                                        i, j, Warning.NO_ROAD);
                                }
                                break;
                            case NO_POWER:
                                if (this.isConnectedToRes(CellType.WATER, i, j)
                                    && this.isConnectedToRoad(i, j)) {
                                    this.checkResConnection(
                                        CellType.POWER, Warning.CONNECTED, i, j);
                                } else {
                                    this.gridHandler.setZoneConnectionWarning(
                                        i, j, Warning.NO_WATER);
                                }
                                break;
                            case CONNECTED:
                                if (!this.isConnectedToRoad(i, j)
                                    || !this.isConnectedToRes(
                                    CellType.WATER, i, j)
                                    || !this.isConnectedToRes(
                                    CellType.POWER, i, j)) {
                                    this.gridHandler.setZoneConnectionWarning(
                                        i, j, Warning.NO_ROAD);
                                }
                        }
                        break;
                    case EMPTY_CELL, CROSSROAD, VERTICAL_ROAD, HORIZONTAL_ROAD:
                        this.gridHandler.setZoneConnectionWarning(
                            i, j, Warning.EMPTY);
                        break;
                }
            }
        }
    }

    /**
     * Metoda kontroluje ci sa okolo bunky nachadza cesta ak ano nastavi
     * upozornenie na dalsie v poradi.
     *
     * @param i i-ty riadok
     * @param j j-ty stlpec
     */
    private void checkRoadConnection(int i, int j) {
        for (CellType road :
            CheckBoxType.ROAD.getCellTypes()) {
            if (0 < i && 0 < j) {
                if (this.gridHandler.getOvergroundGrid()[i - 1][j - 1]
                    .name().equals(road.name())) {
                    this.gridHandler.setZoneConnectionWarning(
                        i, j, Warning.NO_WATER);
                }
            }
            if (i < this.m - 1 && j < this.n - 1) {
                if (this.gridHandler.getOvergroundGrid()[i + 1][j + 1]
                    .name().equals(road.name())) {
                    this.gridHandler.setZoneConnectionWarning(
                        i, j, Warning.NO_WATER);
                }
            }
            if (0 < i && j < this.n - 1) {
                if (this.gridHandler.getOvergroundGrid()[i - 1][j + 1]
                    .name().equals(road.name())
                    || this.gridHandler.getOvergroundGrid()[i][j + 1]
                    .name().equals(road.name())) {
                    this.gridHandler.setZoneConnectionWarning(
                        i, j, Warning.NO_WATER);
                }
            }
            if (0 < j && i < this.m - 1) {
                if (this.gridHandler.getOvergroundGrid()[i + 1][j - 1]
                    .name().equals(road.name())
                    || this.gridHandler.getOvergroundGrid()[i][j - 1]
                    .name().equals(road.name())) {
                    this.gridHandler.setZoneConnectionWarning(
                        i, j, Warning.NO_WATER);
                }
            }
            if (0 < i && i < this.m - 1) {
                if (this.gridHandler.getOvergroundGrid()[i + 1][j]
                    .name().equals(road.name())
                    || this.gridHandler.getOvergroundGrid()[i - 1][j]
                    .name().equals(road.name())) {
                    this.gridHandler.setZoneConnectionWarning(
                        i, j, Warning.NO_WATER);
                }
            }
            if (0 < j && j < this.n - 1) {
                if (this.gridHandler.getOvergroundGrid()[i][j + 1]
                    .name().equals(road.name())
                    || this.gridHandler.getOvergroundGrid()[i][j - 1]
                    .name().equals(road.name())) {
                    this.gridHandler.setZoneConnectionWarning(
                        i, j, Warning.NO_WATER);
                }
            }
        }
    }

    /**
     * Metoda kontroluje ci je stale bunka napojena na cestu.
     *
     * @param i i-ty riadok
     * @param j j-ty stlpec
     * @return ci je bunka stale napojena na cestu alebo nie
     */
    private boolean isConnectedToRoad(int i, int j) {
        int roadCounter = 0;
        for (CellType road :
            CheckBoxType.ROAD.getCellTypes()) {
            if (0 < i && 0 < j) {
                if (this.gridHandler.getOvergroundGrid()[i - 1][j - 1]
                    .name().equals(road.name())) {
                    roadCounter++;
                }
            }
            if (i < this.m - 1 && j < this.n - 1) {
                if (this.gridHandler.getOvergroundGrid()[i + 1][j + 1]
                    .name().equals(road.name())) {
                    roadCounter++;
                }
            }
            if (0 < i && j < this.n - 1) {
                if (this.gridHandler.getOvergroundGrid()[i - 1][j + 1]
                    .name().equals(road.name())
                    || this.gridHandler.getOvergroundGrid()[i][j + 1]
                    .name().equals(road.name())) {
                    roadCounter++;
                }
            }
            if (0 < j && i < this.m - 1) {
                if (this.gridHandler.getOvergroundGrid()[i + 1][j - 1]
                    .name().equals(road.name())
                    || this.gridHandler.getOvergroundGrid()[i][j - 1]
                    .name().equals(road.name())) {
                    roadCounter++;
                }
            }
            if (0 < i && i < this.m - 1) {
                if (this.gridHandler.getOvergroundGrid()[i + 1][j]
                    .name().equals(road.name())
                    || this.gridHandler.getOvergroundGrid()[i - 1][j]
                    .name().equals(road.name())) {
                    roadCounter++;
                }
            }
            if (0 < j && j < this.n - 1) {
                if (this.gridHandler.getOvergroundGrid()[i][j + 1]
                    .name().equals(road.name())
                    || this.gridHandler.getOvergroundGrid()[i][j - 1]
                    .name().equals(road.name())) {
                    roadCounter++;
                }
            }
        }
        return roadCounter != 0;
    }

    /**
     * Metoda kontroluje ci sa okolo bunky nachadzaju zdroje ak ano nastavi
     * upozornenie na dalsie v poradi.
     *
     * @param type    typ bunky ktory kontrolujeme
     * @param warning typ upozornenia ktory zapisujeme
     * @param i       i-ty riadok
     * @param j       j-ty stlpec
     */
    private void checkResConnection(CellType type, Warning warning, int i, int j) {
        for (int y = 0; y <= 3; y++) {
            for (int x = 0; x <= 3; x++) {
                if (y <= i && x <= j) {
                    if (this.gridHandler.getUndergroundGrid()[i - y][j - x]
                        .name().equals(type.name())) {
                        this.gridHandler.setZoneConnectionWarning(
                            i, j, warning);
                    }
                }
                if (i < this.m - y && j < this.n - x) {
                    if (this.gridHandler.getUndergroundGrid()[i + y][j + x]
                        .name().equals(type.name())) {
                        this.gridHandler.setZoneConnectionWarning(
                            i, j, warning);
                    }
                }
                if (y <= i && j < this.n - x) {
                    if (this.gridHandler.getUndergroundGrid()[i - y][j + x]
                        .name().equals(type.name())) {
                        this.gridHandler.setZoneConnectionWarning(
                            i, j, warning);
                    }
                }
                if (x <= j && i < this.m - y) {
                    if (this.gridHandler.getUndergroundGrid()[i + y][j - x]
                        .name().equals(type.name())) {
                        this.gridHandler.setZoneConnectionWarning(
                            i, j, warning);
                    }
                }
            }
        }
    }

    /**
     * Metoda kontroluje ci je stale bunka napojena.
     *
     * @param type typ bunky ktory kontrolujeme
     * @param i    i-ty riadok
     * @param j    j-ty stlpec
     * @return ci je bunka stale napojena alebo nie
     */
    private boolean isConnectedToRes(
        CellType type, int i, int j) {
        for (int y = 0; y <= 3; y++) {
            for (int x = 0; x <= 3; x++) {
                if (y <= i && x <= j) {
                    if (this.gridHandler.getUndergroundGrid()[i - y][j - x]
                        .name().equals(type.name())) {
                        return true;
                    }
                }
                if (i < this.m - y && j < this.n - x) {
                    if (this.gridHandler.getUndergroundGrid()[i + y][j + x]
                        .name().equals(type.name())) {
                        return true;
                    }
                }
                if (y <= i && j < this.n - x) {
                    if (this.gridHandler.getUndergroundGrid()[i - y][j + x]
                        .name().equals(type.name())) {
                        return true;
                    }
                }
                if (x <= j && i < this.m - y) {
                    if (this.gridHandler.getUndergroundGrid()[i + y][j - x]
                        .name().equals(type.name())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
