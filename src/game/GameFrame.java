package game;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements Runnable {
    private final GamePanel gamePanel;

    public GameFrame() {

        this.gamePanel = new GamePanel();
        this.gamePanel.requestFocus();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Cellular City");
        this.setResizable(false);
        this.setVisible(true);
        this.add(this.gamePanel);
        this.pack();
        this.startGameLoop();
    }

    @Override
    public void run() {
        int FPS = 60;
        int Frames = 0;
        double timePerFrame = 1000000000.0 / FPS;
        long lastFrame = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        while (true) {
            long now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                this.gamePanel.repaint();
                lastFrame = now;
                Frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                this.gamePanel.setFpsLabel("FPS: " + Frames);
                Frames = 0;
            }
        }
    }

    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
}
