package game;

import enums.CheckBoxType;

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
        int frames = 0;
        double timePerFrame = 1000000000.0 / fps;
        long lastFrame = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        while (true) {
            long now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                this.panel.repaint();
                lastFrame = now;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                this.panel.setFpsLabel("" + frames);
                frames = 0;
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
