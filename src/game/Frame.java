package game;

import ui.Button;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Trieda na zobrazenie okna.
 * !!! To, ze trieda dedi od JFrame mam naucene z internetu. !!!
 */
public class Frame extends JFrame {
    private static final GraphicsDevice device =
        GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    private final Panel panel;

    public Frame(Panel panel) {
        this.panel = panel;
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
