package game;

import ui.Frame;

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
    private final Panel panel;

    public Game() {
        this.panel = new Panel();
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
                this.panel.getAccount().countCells();
                this.panel.getAccount().calculateFees();
                previousFrame = currentFrame;
            }

            /*
            Podmienka zarucuje ze telo sa vykona kazdych 20 sekund. (Imitacia
             mesacneho prijmu).
             */
            if (currentTime - previousTime >= 20 * SECOND) {
                this.panel.getAccount().calculateIncome();
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
