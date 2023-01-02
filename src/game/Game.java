package game;

public class Game implements Runnable {
    private static final int FPS = 60;
    private static final int SECOND = 1000;
    private final Panel panel;

    public Game() {
        this.panel = new Panel();
        new Frame(this.panel);
        this.startGameLoop();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS; // secunda v nano sekundach
        long previousFrame = System.nanoTime();
        long previousTime = System.currentTimeMillis();

        while (true) {
            long currentFrame = System.nanoTime();
            if (currentFrame - previousFrame >= timePerFrame) {
                this.panel.repaint();
                this.panel.checkBoxesAction();
                this.panel.getAccount().countCells();
                this.panel.getAccount().calculateFees();
                previousFrame = currentFrame;
            }

            if (System.currentTimeMillis() - previousTime >= 20 * SECOND) {
                this.panel.getAccount().calculateIncome();
                previousTime = System.currentTimeMillis();
            }
        }
    }

    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
}
