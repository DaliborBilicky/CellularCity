package game;

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

    public Game() {
        Save save = new Save();
        Grid grid = new Grid(save);
        this.account = new Account(grid);
        MouseInput mouseInput = new MouseInput(grid, this.account);
        Canvas canvas = new Canvas();
        this.panel = new Panel(
            grid, canvas, mouseInput, this.account);
        new Frame(this.panel);
        this.startGameLoop();
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
