package game;

import tools.Image;

import javax.swing.JFrame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Frame extends JFrame {
    private static final GraphicsDevice device =
        GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    public Frame(Panel panel) {
        this.setTitle("Cellular City");
        this.setIconImage(new Image().getImageIcon(
            "res/zones/Commercial.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);
        this.setVisible(true);
        this.add(panel);
        this.pack();
        device.setFullScreenWindow(this);
        panel.requestFocus();
    }
}
