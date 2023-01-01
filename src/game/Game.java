package game;

public class Game implements Runnable {
    private final Panel panel;

    public Game() {
        this.panel = new Panel();
        new Frame(this.panel);
        this.startGameLoop();
    }

    /**
     * Metoda na obnovovanie platna v urcenej hodnote fps.
     * !!! Metoda je z internetu. Link v dokumentacii. !!!
     */
    @Override
    public void run() {
        int fps = 60;
        double timePerFrame = 1000000000.0 / fps;
        long lastFrame = System.nanoTime();
        long lastCurrentTime = System.currentTimeMillis();

        while (true) {
            long now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                this.panel.repaint();
                this.panel.getGraph().setRightGraph(this.panel.getGrid().getOvergroundGrid());
                this.panel.checkBoxesFunction();
                this.panel.getMoneyBar().countCells(
                    this.panel.getGrid().getOvergroundGrid(),
                    this.panel.getGrid().getUndergroundGrid());
                this.panel.getMoneyBar().calculateFees();
                this.panel.getMoneyBar().setBar();
                lastFrame = now;
            }

            if (System.currentTimeMillis() - lastCurrentTime >= 30000) {
                lastCurrentTime = System.currentTimeMillis();
                this.panel.getMoneyBar().calculateIncome();
            }
        }
    }

    /**
     * Metoda spusti game loop.
     * !!! Metoda je z internetu (spolu s run()). Link v dokumentacii. !!!
     */
    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
}
