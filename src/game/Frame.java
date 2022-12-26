package game;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.*;

/**
 * Trieda na zobrazenie okna.
 * !!! To, ze trieda dedi od JFrame mam naucene z internetu. !!!
 */
public class Frame extends JFrame {
    private static final GraphicsDevice device =
        GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    public Frame(Panel panel) {
        ImageIcon image = new ImageIcon("res/zones/Commercial.png");

        panel.requestFocus();
        this.setTitle("Cellular City");
        this.setIconImage(image.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);
        this.setVisible(true);
        this.add(panel);
        this.pack();
        device.setFullScreenWindow(this);
    }
}
