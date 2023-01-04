package ui;

import game.Panel;
import tools.Image;

import javax.swing.JFrame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


/**
 * Trieda sluzi na zobrazenie okna v pocitaci.
 */
public class Frame extends JFrame {
    /**
     * Konstruktor nastavuje hodnoty okna podla poziadaviek.
     *
     * @param panel konstrukto potrebuje Panel aby ho priradal.
     */
    public Frame(Panel panel) {
        /*
        Riadok kodu som si pozical z projektu ShapesGE aby som mohol dat
        svoju aplikaciu na fullscreen.
         */
        GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];


        this.setTitle("Cellular City");
        this.setIconImage(new Image().getImageIcon(
            "res/zones/Commercial.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);
        this.setVisible(true);
        this.add(panel);
        this.pack();
        // Riadok zo ShapesGE
        device.setFullScreenWindow(this);
        panel.requestFocus();
    }
}
