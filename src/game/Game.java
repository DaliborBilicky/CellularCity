package game;

import enums.CellType;
import enums.CheckBoxType;
import enums.Warning;
import tools.Canvas;
import tools.Grid;
import tools.MouseInput;
import tools.Save;

/**
 * Trieda sa stara o spravne bezanie aplikacie.
 */
public class Game implements Runnable {
    /**
     * FPS je hodnota ako casto sa obnovuje aplikacia.
     * SECOND je hodnota sekundy v milisekundach.
     */
    private static final int FPS = 60;
    private static final int SECOND = 1000;
    private final Account account;
    private final Panel panel;
    private final Grid grid;

    public Game() {
        Save save = new Save();
        this.grid = new Grid(save);
        this.account = new Account(grid);
        MouseInput mouseInput = new MouseInput(grid, this.account);
        Canvas canvas = new Canvas();
        this.panel = new Panel(
            grid, canvas, mouseInput, this.account);
        new Frame(this.panel);
        this.startGameLoop();
    }

    public void checkConnection() {
        int m = this.grid.getOvergroundGrid().length;
        int n = this.grid.getOvergroundGrid()[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                switch (this.grid.getOvergroundGrid()[i][j]) {
                    case RESIDENTIAL, COMMERCIAL, INDUSTRIAL:
                        switch (this.grid.getZonesConnection()[i][j]) {
                            case EMPTY:
                                this.grid.setZoneConnection(i, j, Warning.NO_ROAD);
                                break;
                            case NO_ROAD:
                                this.roadCheck(i, j, m, n);
                                break;
                            case NO_WATER:
                                this.waterCheck(i, j, m, n);
                                break;
                            case NO_POWER:
                                this.powerCheck(i, j, m, n);
                                break;
                        }
                        break;
                    case EMPTY_CELL:
                        this.grid.setZoneConnection(i, j, Warning.EMPTY);
                        break;
                }
            }
        }
    }

    private void roadCheck(int i, int j, int m, int n) {
        for (CellType road :
            CheckBoxType.ROAD.getCellTypes()) {
            if (0 < i && i < m - 1) {
                if (this.grid.getOvergroundGrid()[i + 1][j]
                    .name().equals(road.name())
                    || this.grid.getOvergroundGrid()[i - 1][j]
                    .name().equals(road.name())) {
                    this.grid.setZoneConnection(i, j,
                        Warning.NO_WATER);
                }
            }
            if (0 < j && j < n - 1) {
                if (this.grid.getOvergroundGrid()[i][j + 1]
                    .name().equals(road.name())
                    || this.grid.getOvergroundGrid()[i][j - 1]
                    .name().equals(road.name())) {
                    this.grid.setZoneConnection(i, j,
                        Warning.NO_WATER);
                }
            }
        }
    }

    private void waterCheck(int i, int j, int m, int n) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (x <= i && i < m - x
                    && y <= j && j < n - y) {
                    if (this.grid.getUndergroundGrid()[i + x][j + y]
                        .name().equals(CellType.WATER.name())
                        || this.grid.getUndergroundGrid()[i + x][j - y]
                        .name().equals(CellType.WATER.name())
                        || this.grid.getUndergroundGrid()[i - x][j + y]
                        .name().equals(CellType.WATER.name())
                        || this.grid.getUndergroundGrid()[i - x][j - y]
                        .name().equals(CellType.WATER.name())) {
                        this.grid.setZoneConnection(i, j,
                            Warning.NO_POWER);
                    }
                }
            }
        }
    }

    private void powerCheck(int i, int j, int m, int n) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (x <= i && i < m - x
                    && y <= j && j < n - y) {
                    if (this.grid.getUndergroundGrid()[i + x][j + y]
                        .name().equals(CellType.POWER.name())
                        || this.grid.getUndergroundGrid()[i + x][j - y]
                        .name().equals(CellType.POWER.name())
                        || this.grid.getUndergroundGrid()[i - x][j + y]
                        .name().equals(CellType.POWER.name())
                        || this.grid.getUndergroundGrid()[i - x][j - y]
                        .name().equals(CellType.POWER.name())) {
                        this.grid.setZoneConnection(i, j,
                            Warning.CONNECTED);
                    }
                }
            }
        }
    }

    /**
     * Metoda obmedzuje bezanie hry podla podmienok.
     * !!! Metoda je z internetu. !!!
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS; // secunda v nano sekundach
        long previousFrame = System.nanoTime();
        long previousTime = System.currentTimeMillis();

        while (true) {
            long currentFrame = System.nanoTime();
            long currentTime = System.currentTimeMillis();

            /*
            Podmienka zarucuje ze telo sa vykona 60x za sekundu.
             */
            if (currentFrame - previousFrame >= timePerFrame) {
                this.panel.repaint();
                this.panel.checkBoxesAction();
                this.panel.setCheckBoxesLook();

                this.account.countCells();
                this.account.calculateFees();

                this.checkConnection();

                previousFrame = currentFrame;
            }

            /*
            Podmienka zarucuje ze telo sa vykona kazdych 20 sekund. (Imitacia
             mesacneho prijmu).
             */
            if (currentTime - previousTime >= 20 * SECOND) {
                this.account.calculateIncome();
                previousTime = System.currentTimeMillis();
            }
        }
    }

    /**
     * !!! Metoda je z internetu. !!!
     */
    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
}
