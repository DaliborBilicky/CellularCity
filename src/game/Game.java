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
        for (int i = 0; i < this.grid.getOvergroundGrid().length; i++) {
            for (int j = 0; j < this.grid.getOvergroundGrid()[i].length; j++) {
                switch (this.grid.getOvergroundGrid()[i][j]) {
                    case RESIDENTIAL, COMMERCIAL, INDUSTRIAL:
                        this.grid.setZoneConnectionToRoad(i, j,
                            Warning.NO_ROAD);
                        for (CellType road :
                            CheckBoxType.ROAD.getCellTypes()) {
                            if (0 < i && i < this.grid.getOvergroundGrid().length - 1
                                || 0 < j && j < this.grid.getOvergroundGrid()[i].length - 1) {


                                if (this.grid.getOvergroundGrid()[i + 1][j]
                                    .name().equals(road.name())
                                    || this.grid.getOvergroundGrid()[i][j + 1]
                                    .name().equals(road.name())) {
                                    this.grid.setZoneConnectionToRoad(
                                        i, j, Warning.EMPTY);

                                } else if (this.grid.getOvergroundGrid()[i - 1][j]
                                    .name().equals(road.name())
                                    || this.grid.getOvergroundGrid()[i][j - 1]
                                    .name().equals(road.name())) {
                                    this.grid.setZoneConnectionToRoad(
                                        i, j, Warning.EMPTY);
                                }
                            }
                        }
                        break;
                    case EMPTY_CELL:
                        break;
                }
            }
        }
    }

    /**
     * Metoda obmedzuje bezanie hry podla podmienok.
     * !!! Metoda je z internetu. !!!
     * https://www.youtube.com/watch?v=aFS9Whsoecc
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
