package game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class GameFrame extends JFrame implements Runnable {
    private final GamePanel gamePanel;
    private final JLabel label;

    public GameFrame() {
        this.gamePanel = new GamePanel();
        this.label = new JLabel();
        ImageIcon image = new ImageIcon("res/Commercial.png");

        this.gamePanel.requestFocus();
        this.setTitle("Cellular City");
        this.setIconImage(image.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        this.add(this.gamePanel);
        this.pack();
        this.startGameLoop();
    }

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
                this.gamePanel.repaint();
                lastFrame = now;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                this.setFpsLabel("FPS: " + frames);
                frames = 0;
            }
        }
    }

    private void setFpsLabel(String text) {
        this.label.setFont(new Font("Arial", Font.BOLD, 12));
        this.label.setBounds(0, 0, 60, 20);
        this.label.setForeground(Color.GREEN);
        this.label.setText(text);
        this.gamePanel.add(this.label);
    }

    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
}
